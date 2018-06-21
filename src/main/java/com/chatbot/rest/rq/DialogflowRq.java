package com.chatbot.rest.rq;

import com.chatbot.model.OriginalDetectIntentRequest;
import com.chatbot.model.QueryResult;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class DialogflowRq {

    private String responseId;
    private QueryResult queryResult;
    private OriginalDetectIntentRequest originalDetectIntentRequest;
    private String session;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
        return originalDetectIntentRequest;
    }

    public void setOriginalDetectIntentRequest(OriginalDetectIntentRequest originalDetectIntentRequest) {
        this.originalDetectIntentRequest = originalDetectIntentRequest;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
