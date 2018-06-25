package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "text_response")
public class DBTextResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "text_response_id")
    private Long textResponseId;

    @Column(name = "text")
    private String text;

    public Long getTextResponseId() {
        return textResponseId;
    }

    public void setTextResponseId(Long textResponseId) {
        this.textResponseId = textResponseId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
