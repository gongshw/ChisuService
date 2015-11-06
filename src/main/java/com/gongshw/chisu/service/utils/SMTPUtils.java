/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.utils;

import static java.lang.String.format;

import java.util.Arrays;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gongshiwei
 */
public class SMTPUtils {
    private static final Logger logger = LoggerFactory.getLogger(SMTPUtils.class);

    /**
     * Copied from http://eyeasme.com/Shayne/MAILHOSTS/mailHostsLookup.html
     *
     * @param domainName domain to resolve
     *
     * @return an array of resolved mx domain
     *
     * @throws NamingException
     */
    public static String[] lookupMailHosts(String domainName) throws NamingException {
        // see: RFC 974 - Mail routing and the domain system
        // see: RFC 1034 - Domain names - concepts and facilities
        // see: http://java.sun.com/j2se/1.5.0/docs/guide/jndi/jndi-dns.html
        //    - DNS Service Provider for the Java Naming Directory Interface (JNDI)

        // get the default initial Directory Context
        InitialDirContext iDirC = new InitialDirContext();
        // get the MX records from the default DNS directory service provider
        //    NamingException thrown if no DNS record found for domainName
        Attributes attributes = iDirC.getAttributes("dns:/" + domainName, new String[] {"MX"});
        // attributeMX is an attribute ('list') of the Mail Exchange(MX) Resource Records(RR)
        Attribute attributeMX = attributes.get("MX");

        // if there are no MX RRs then default to domainName (see: RFC 974)
        if (attributeMX == null) {
            return (new String[] {domainName});
        }

        // split MX RRs into Preference Values(pvhn[0]) and Host Names(pvhn[1])
        String[][] pvhn = new String[attributeMX.size()][2];
        for (int i = 0; i < attributeMX.size(); i++) {
            pvhn[i] = ("" + attributeMX.get(i)).split("\\s+");
        }

        // sort the MX RRs by RR value (lower is preferred)
        Arrays.sort(pvhn, (o1, o2) -> (Integer.parseInt(o1[0]) - Integer.parseInt(o2[0])));

        // put sorted host names in an array, get rid of any trailing '.'
        String[] sortedHostNames = new String[pvhn.length];
        for (int i = 0; i < pvhn.length; i++) {
            sortedHostNames[i] = pvhn[i][1].endsWith(".") ?
                    pvhn[i][1].substring(0, pvhn[i][1].length() - 1) : pvhn[i][1];
        }
        return sortedHostNames;
    }

    public static String getAddress(String email) {
        return email.split("@")[1];
    }

    public static String getUserName(String email) {
        return email.split("@")[0];
    }

    public static void sendEmail(String from, String to, String subject, String content) throws EmailException {
        String[] mxHosts;
        try {
            String targetHost = SMTPUtils.getAddress(to);
            mxHosts = SMTPUtils.lookupMailHosts(targetHost);
            logger.info(format("resolving mx dns of %s: %s", targetHost, StringUtils.join(mxHosts, ",")));
        } catch (NamingException e) {
            e.printStackTrace();
            throw new EmailException(e);
        }

        for (String host : mxHosts) {
            try {
                Email email = new HtmlEmail();
                email.setHostName(host);
                email.setFrom(from);
                email.setCharset("UTF-8");
                email.setSubject(subject);
                email.setMsg(content);
                email.addTo(to);
                email.send();
                break;
            } catch (EmailException e) {
                e.printStackTrace();
                logger.info(format("send fail using SMTP host %s, trying another SMTP host.", host));
            }
        }
    }
}
