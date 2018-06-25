package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "card_buttons")
public class DBCardButtons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_button_id")
    private Long cardButtonId;

    @Column(name = "card_button_text")
    private String cardButtonText;

    @Column(name = "card_button_postback_url")
    private String cardButtonPostbackUrl;

    @Column(name = "card_response_id")
    private Long cardResponseId;

    public Long getCardButtonId() {
        return cardButtonId;
    }

    public void setCardButtonId(Long cardButtonId) {
        this.cardButtonId = cardButtonId;
    }

    public String getCardButtonText() {
        return cardButtonText;
    }

    public void setCardButtonText(String cardButtonText) {
        this.cardButtonText = cardButtonText;
    }

    public String getCardButtonPostbackUrl() {
        return cardButtonPostbackUrl;
    }

    public void setCardButtonPostbackUrl(String cardButtonPostbackUrl) {
        this.cardButtonPostbackUrl = cardButtonPostbackUrl;
    }

    public Long getCardResponseId() {
        return cardResponseId;
    }

    public void setCardResponseId(Long cardResponseId) {
        this.cardResponseId = cardResponseId;
    }
}
