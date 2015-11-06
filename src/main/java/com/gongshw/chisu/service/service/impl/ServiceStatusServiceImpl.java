/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.gongshw.chisu.service.service.model.ServiceStatus;
import com.gongshw.chisu.service.utils.SystemConstant;

/**
 * @author gongshiwei
 */
@Service
public class ServiceStatusServiceImpl implements com.gongshw.chisu.service.service.ServiceStatusService {
    @Override
    public ServiceStatus getServiceStatus() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setTime(new Date());
        serviceStatus.setVersion(SystemConstant.SERVICE_VERSION);
        return serviceStatus;
    }
}
