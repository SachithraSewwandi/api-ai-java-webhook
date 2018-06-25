package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "card_response")
public class DBCardResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_response_id")
    private Long cardResponseId;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    public Long getCardResponseId() {
        return cardResponseId;
    }

    public void setCardResponseId(Long cardResponseId) {
        this.cardResponseId = cardResponseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
