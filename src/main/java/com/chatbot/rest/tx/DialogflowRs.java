package com.chatbot.rest.tx;

import com.chatbot.model.FulfillmentMessage;
import com.chatbot.model.OriginalDetectIntentRequest;
import com.chatbot.model.QueryResult;

import java.util.List;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class DialogflowRs {

    private String fulfillmentText;
    private List<FulfillmentMessage> fulfillmentMessages;
    private String source;

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public List<FulfillmentMessage> getFulfillmentMessages() {
        return fulfillmentMessages;
    }

    public void setFulfillmentMessages(List<FulfillmentMessage> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
