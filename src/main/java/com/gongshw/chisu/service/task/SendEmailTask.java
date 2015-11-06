/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gongshw.chisu.service.service.DelayEmailService;

/**
 * @author gongshiwei
 */
@Component
public class SendEmailTask {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DelayEmailService delayEmailService;

    @Scheduled(fixedDelayString = "${chisu.email.sendInterval}")
    public void reportCurrentTime() {
        logger.debug("start send delay email!");
        int emailNum = delayEmailService.sendEmails();
        logger.debug("{} delay emails sent!", emailNum);
    }
}
