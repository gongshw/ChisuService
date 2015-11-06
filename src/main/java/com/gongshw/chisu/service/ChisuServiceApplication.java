/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author gongshiwei
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class ChisuServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChisuServiceApplication.class, args);
    }
}
