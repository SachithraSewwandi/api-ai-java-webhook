package com.chatbot.model;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class OriginalDetectIntentRequest {

    private String source;
    private Payload payload;
    private String timestamp;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
