package com.chatbot.rest.model;

/**
 * Created by sewwandiwi on 6/25/2018.
 */
public class IntentPrecentage {

    private Long intentID;
    private String intentName;
    private int intentPrecentage;
    private Long intentCount;

    public Long getIntentID() {
        return intentID;
    }

    public void setIntentID(Long intentID) {
        this.intentID = intentID;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public int getIntentPrecentage() {
        return intentPrecentage;
    }

    public void setIntentPrecentage(int intentPrecentage) {
        this.intentPrecentage = intentPrecentage;
    }

    public Long getIntentCount() {
        return intentCount;
    }

    public void setIntentCount(Long intentCount) {
        this.intentCount = intentCount;
    }
}
