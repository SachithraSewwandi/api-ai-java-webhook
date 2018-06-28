package com.chatbot.rest.model;

import java.util.Date;
import java.util.List;

/**
 * Created by sewwandiwi on 6/28/2018.
 */
public class UserMessage {

    private String userName;
    private List<String> userMessage;
    private Date timeStamp;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(List<String> userMessage) {
        this.userMessage = userMessage;
    }
}
