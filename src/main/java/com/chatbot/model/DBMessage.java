package com.chatbot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "message")
public class DBMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "message_time_stamp")
    private Date messageTimeStamp;

    @Column(name = "intent_id")
    private Long intentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "message")
    private String message;

    @Column(name = "platform_user_id")
    private String platformUserId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Date getMessageTimeStamp() {
        return messageTimeStamp;
    }

    public void setMessageTimeStamp(Date messageTimeStamp) {
        this.messageTimeStamp = messageTimeStamp;
    }

    public Long getIntentId() {
        return intentId;
    }

    public void setIntentId(Long intentId) {
        this.intentId = intentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId;
    }
}
