/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service;

import com.gongshw.chisu.service.service.model.Email;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author gongshiwei
 */
@JsonRpcService("EmailSendService.json")
public interface EmailSendService {
    void sendEmail(Email email);
}
