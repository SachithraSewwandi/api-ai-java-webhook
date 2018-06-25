package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/22/2018.
 */
@Entity(name = "response_type")
public class DBResponseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "response_type_id")
    private Long responseTypeId;

    @Column(name = "response_type")
    private String responseType;

    public Long getResponseTypeId() {
        return responseTypeId;
    }

    public void setResponseTypeId(Long responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
