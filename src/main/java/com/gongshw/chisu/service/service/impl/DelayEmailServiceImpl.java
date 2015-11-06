/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service.impl;

import java.security.AccessControlException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gongshw.chisu.service.service.DelayEmailService;
import com.gongshw.chisu.service.service.EmailSendService;
import com.gongshw.chisu.service.service.RecipeService;
import com.gongshw.chisu.service.service.model.Email;
import com.gongshw.chisu.service.mapper.DelayEmailMapper;
import com.gongshw.chisu.service.mapper.RecipeMapper;
import com.gongshw.chisu.service.model.DelayEmail;
import com.gongshw.chisu.service.model.Recipe;

/**
 * @author gongshiwei
 */
@Service
public class DelayEmailServiceImpl implements DelayEmailService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${chisu.email.server}")
    private String emailServerAddress;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private DelayEmailMapper delayEmailMapper;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private EmailSendService emailSendService;

    @Override
    public void storeDelayEmail(Email email) {
        long recipeId = getRecipeIdFromEmailAddress(email.getReceiverAddress());
        Recipe recipe = recipeMapper.getRecipeById(recipeId);
        if (recipe == null) {
            throw new IllegalArgumentException(String.format("recipe %s not exist", recipeId));
        }
        if (!recipe.getCreatorEmail().equals(email.getSenderAddress())) {
            throw new AccessControlException(String.format("user %s cannot access recipe %d",
                    email.getSenderAddress(), recipeId));
        }
        DelayEmail delayEmail = new DelayEmail();
        delayEmail.setSubject(email.getSubject());
        delayEmail.setContent(email.getContent());
        delayEmail.setRecipeId(recipeId);
        Date now = new Date();
        delayEmail.setReceiveTime(now);
        delayEmail.setPlanSendTime(recipeService.getPlanSendTime(now, recipe));
        delayEmailMapper.insertDelayEmail(delayEmail);
        logger.info("email[{}, {}->{}] stored", email.getSubject(), recipe.getCreatorEmail(), recipe.getTargetEmail());
    }

    @Override
    public long getRecipeIdFromEmailAddress(@NotNull String emailAddress) {
        if (emailAddress.endsWith("@" + emailServerAddress)) {
            Matcher matcher = Pattern.compile("^recipe(\\d+)@.*").matcher(emailAddress);
            if (matcher.matches()) {
                String idStr = matcher.group(1);
                return Long.parseLong(idStr);
            }
            throw new IllegalArgumentException("email is not a delay email");
        }
        throw new IllegalArgumentException("email is not sent to chisu server");
    }

    @Override
    public String getRecipeEmailById(long id) {
        return "recipe" + id + "@" + emailServerAddress;
    }

    @Override
    public int countAllDelayEmailsByEmail(String email) {
        return delayEmailMapper.countAllDelayEmailsByEmail(email);
    }

    @Override
    public int sendEmails() {
        int emailSent = 0;
        Date now = new Date();
        for (DelayEmail delayEmail : delayEmailMapper.getEmailToSend(now)) {
            Recipe recipe = recipeMapper.getRecipeById(delayEmail.getRecipeId());
            logger.debug("found email[{}, {}->{}] to send",
                    delayEmail.getSubject(), recipe.getCreatorEmail(), recipe.getCreatorEmail());
            Email email = new Email();
            email.setSenderAddress(getRecipeEmailById(recipe.getId()));
            email.setReceiverAddress(recipe.getTargetEmail());
            email.setSubject(delayEmail.getSubject());
            email.setContent(delayEmail.getContent());
            emailSendService.sendEmail(email);
            delayEmailMapper.markAsSent(delayEmail.getId(), now);
            emailSent++;
        }
        return emailSent;
    }
}
