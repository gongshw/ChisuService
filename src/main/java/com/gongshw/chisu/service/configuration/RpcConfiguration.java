/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceExporter;

/**
 * @author gongshiwei
 */
@Configuration
public class RpcConfiguration {
    @Bean
    public AutoJsonRpcServiceExporter getAutoJsonRpcServiceExporter() {
        return new AutoJsonRpcServiceExporter();
    }
}
