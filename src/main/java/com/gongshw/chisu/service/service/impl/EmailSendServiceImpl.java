/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service.impl;

import static java.lang.String.format;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gongshw.chisu.service.service.model.Email;
import com.gongshw.chisu.service.utils.SMTPUtils;

/**
 * @author gongshiwei
 */
@Service
public class EmailSendServiceImpl implements com.gongshw.chisu.service.service.EmailSendService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void sendEmail(Email email) {
        try {
            SMTPUtils.sendEmail(email.getSenderAddress(), email.getReceiverAddress(), email.getSubject(),
                    email.getContent());
        } catch (EmailException e) {
            logger.error(format("fail to send message to %s", email.getReceiverAddress()), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
