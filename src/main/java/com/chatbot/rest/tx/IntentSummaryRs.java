package com.chatbot.rest.tx;

import com.chatbot.rest.model.IntentPrecentage;

import java.util.List;

/**
 * Created by sewwandiwi on 6/25/2018.
 */
public class IntentSummaryRs {

    private Long totalQueries;
    private List<IntentPrecentage> intentPrecentageList;
    private int code;
    private String message;

    public Long getTotalQueries() {
        return totalQueries;
    }

    public void setTotalQueries(Long totalQueries) {
        this.totalQueries = totalQueries;
    }

    public List<IntentPrecentage> getIntentPrecentageList() {
        return intentPrecentageList;
    }

    public void setIntentPrecentageList(List<IntentPrecentage> intentPrecentageList) {
        this.intentPrecentageList = intentPrecentageList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
