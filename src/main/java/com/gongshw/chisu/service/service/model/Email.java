/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.chisu.service.service.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author gongshiwei
 */
public class Email {
    /**
     * 发送者地址
     */
    @org.hibernate.validator.constraints.Email
    @NotEmpty
    String senderAddress;

    /**
     * 接受者地址
     */
    @org.hibernate.validator.constraints.Email
    @NotEmpty
    String receiverAddress;

    /**
     * 主题
     */
    @NotEmpty
    String subject;

    /**
     * 内容
     */
    @NotEmpty
    String content;

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
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
