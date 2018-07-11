package com.chatbot.rest.tx;

import com.chatbot.model.DBIntent;

import java.util.List;

/**
 * Created by sewwandiwi on 7/3/2018.
 */
public class IntentList {

    private List<DBIntent> intentList;

    public List<DBIntent> getIntentList() {
        return intentList;
    }

    public void setIntentList(List<DBIntent> intentList) {
        this.intentList = intentList;
    }
}
