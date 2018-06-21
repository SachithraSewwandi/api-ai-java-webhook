package com.chatbot.model;

/**
 * Created by sewwandiwi on 6/21/2018.
 */
public class FulfillmentMessage {

    private Text text;
    private Card card;
    private QuickReplies quickReplies;
    private String platform;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public QuickReplies getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(QuickReplies quickReplies) {
        this.quickReplies = quickReplies;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
