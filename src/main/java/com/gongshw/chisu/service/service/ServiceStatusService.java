/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service;

import com.gongshw.chisu.service.service.model.ServiceStatus;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author gongshiwei
 */
@JsonRpcService("ServiceStatusService.json")
public interface ServiceStatusService {
    ServiceStatus getServiceStatus();
}
