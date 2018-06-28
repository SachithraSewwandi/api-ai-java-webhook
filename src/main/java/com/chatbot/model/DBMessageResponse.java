package com.chatbot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sewwandiwi on 6/28/2018.
 */
@Entity(name = "message_response")
public class DBMessageResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_response_id")
    private Long messageResponseId;

    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "intent_id")
    private Long intentId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "response_id")
    private Long responseId;

    @Column(name = "response_type_id")
    private Long responseTypeId;


    @Column(name = "time_stamp")
    private Date timeStamp;


    public Long getMessageResponseId() {
        return messageResponseId;
    }

    public void setMessageResponseId(Long messageResponseId) {
        this.messageResponseId = messageResponseId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getIntentId() {
        return intentId;
    }

    public void setIntentId(Long intentId) {
        this.intentId = intentId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Long getResponseTypeId() {
        return responseTypeId;
    }

    public void setResponseTypeId(Long responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
