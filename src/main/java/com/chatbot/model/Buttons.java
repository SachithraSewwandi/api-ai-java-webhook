package com.chatbot.model;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class Buttons {
    private String text;
    private String postback;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostback() {
        return postback;
    }

    public void setPostback(String postback) {
        this.postback = postback;
    }
}
