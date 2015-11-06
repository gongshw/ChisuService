/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.model;

import java.util.Date;

/**
 * 延迟发送计划
 *
 * @author gongshiwei
 */
public class Recipe {
    /**
     * 计划id
     */
    private long id;

    /**
     * 所有者名称
     */
    private String creatorName;

    /**
     * 所有者邮箱地址
     */
    private String creatorEmail;

    /**
     * 目标名称
     */
    private String targetName;

    /**
     * 目标地址
     */
    private String targetEmail;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 延迟发送策略id
     */
    private long delayStrategyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetEmail() {
        return targetEmail;
    }

    public void setTargetEmail(String targetEmail) {
        this.targetEmail = targetEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getDelayStrategyId() {
        return delayStrategyId;
    }

    public void setDelayStrategyId(long delayStrategyId) {
        this.delayStrategyId = delayStrategyId;
    }
}
