package com.chatbot.rest.tx;

import java.util.List;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class DialogflowRs {

    private String fulfillmentText;
    private List<Object> fulfillmentMessages;
    //private String fulfillmentMessages;
    private String source;

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public List<Object> getFulfillmentMessages() {
        return fulfillmentMessages;
    }

    public void setFulfillmentMessages(List<Object> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
