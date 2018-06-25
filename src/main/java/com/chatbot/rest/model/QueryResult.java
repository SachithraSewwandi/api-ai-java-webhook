package com.chatbot.rest.model;

import java.util.List;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class QueryResult {

    private String queryText;
    private String action;
    private Parameters parameters;
    private String allRequiredParamsPresent;
    private List<OutputContexts> outputContexts;
    private Intent intent;
    private String languageCode;
    private String intentDetectionConfidence;
    private String fulfillmentText;
    private List<FulfillmentMessage> fulfillmentMessages;



    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getAllRequiredParamsPresent() {
        return allRequiredParamsPresent;
    }

    public void setAllRequiredParamsPresent(String allRequiredParamsPresent) {
        this.allRequiredParamsPresent = allRequiredParamsPresent;
    }

    public List<OutputContexts> getOutputContexts() {
        return outputContexts;
    }

    public void setOutputContexts(List<OutputContexts> outputContexts) {
        this.outputContexts = outputContexts;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getIntentDetectionConfidence() {
        return intentDetectionConfidence;
    }

    public void setIntentDetectionConfidence(String intentDetectionConfidence) {
        this.intentDetectionConfidence = intentDetectionConfidence;
    }

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
}
