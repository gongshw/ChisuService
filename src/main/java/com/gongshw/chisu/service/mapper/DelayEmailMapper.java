/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gongshw.chisu.service.model.DelayEmail;

/**
 * @author gongshiwei
 */
public interface DelayEmailMapper {
    void insertDelayEmail(DelayEmail delayEmail);

    int countAllDelayEmailsByEmail(@Param("email") String email);

    List<DelayEmail> getEmailToSend(@Param("sendTime") Date sendTime);

    void markAsSent(@Param("id") long id, @Param("sendTime") Date sendTime);
}
