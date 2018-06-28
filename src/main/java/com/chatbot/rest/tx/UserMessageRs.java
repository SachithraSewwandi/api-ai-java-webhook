package com.chatbot.rest.tx;

import com.chatbot.rest.model.UserMessage;

import java.util.List;

/**
 * Created by sewwandiwi on 6/28/2018.
 */
public class UserMessageRs {

    List<UserMessage> userMessageList;

    public List<UserMessage> getUserMessageList() {
        return userMessageList;
    }

    public void setUserMessageList(List<UserMessage> userMessageList) {
        this.userMessageList = userMessageList;
    }
}
