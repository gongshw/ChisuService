/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service;

import javax.validation.constraints.NotNull;

import com.gongshw.chisu.service.service.model.Email;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author gongshiwei
 */
@JsonRpcService("DelayEmailService.json")
public interface DelayEmailService {
    void storeDelayEmail(Email email);

    long getRecipeIdFromEmailAddress(@NotNull String emailAddress);

    String getRecipeEmailById(long id);

    int countAllDelayEmailsByEmail(String email);

    int sendEmails();
}
