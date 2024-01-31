package com.vini.socket.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vini.socket.enums.MessageTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StructuredMessage {
    private String userId, type, content;
    private MessageTypeEnum messageType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MessageTypeEnum getMessageType() throws IllegalArgumentException {
        if (this.messageType == null) {
            this.messageType = MessageTypeEnum.valueOf(this.type);
        }
        return this.messageType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
