/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.model;

import java.util.Date;

/**
 * @author gongshiwei
 */
public class DelayEmail {
    /**
     * id
     */
    private long id;

    /**
     * 发送计划id
     */
    private long recipeId;

    /**
     * 服务收到邮件的时间
     */
    private Date receiveTime;

    /**
     * 计划发送到目标的时间
     */
    private Date planSendTime;

    /**
     * 实际发送到目标的时间
     */
    private Date realSendTime;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getPlanSendTime() {
        return planSendTime;
    }

    public void setPlanSendTime(Date planSendTime) {
        this.planSendTime = planSendTime;
    }

    public Date getRealSendTime() {
        return realSendTime;
    }

    public void setRealSendTime(Date realSendTime) {
        this.realSendTime = realSendTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
