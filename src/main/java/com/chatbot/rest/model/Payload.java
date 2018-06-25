package com.chatbot.rest.model;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class Payload {

    private User sender;
    private User recipient;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
