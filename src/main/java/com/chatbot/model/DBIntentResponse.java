package com.chatbot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "intent_response")
public class DBIntentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "intent_response_id")
    private Long intentResponseId;

    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "response_type_id")
    private Long responseTypeId;

    @Column(name = "status")
    private String status;

    @Column(name = "added_date")
    private Date addedDate;

    @Column(name = "intent_id")
    private Long intentId;

    @Column(name = "response_order")
    private Long responseOrder;

    @Column(name = "response_id")
    private Long responseId;

    public Long getIntentResponseId() {
        return intentResponseId;
    }

    public void setIntentResponseId(Long intentResponseId) {
        this.intentResponseId = intentResponseId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getResponseTypeId() {
        return responseTypeId;
    }

    public void setResponseTypeId(Long responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Long getIntentId() {
        return intentId;
    }

    public void setIntentId(Long intentId) {
        this.intentId = intentId;
    }

    public Long getResponseOrder() {
        return responseOrder;
    }

    public void setResponseOrder(Long responseOrder) {
        this.responseOrder = responseOrder;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }
}
