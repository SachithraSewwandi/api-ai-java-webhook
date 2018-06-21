package com.chatbot.model;

import java.util.List;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class QuickReplies {

    private String title;
    private List<String> quickReplies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(List<String> quickReplies) {
        this.quickReplies = quickReplies;
    }
}
