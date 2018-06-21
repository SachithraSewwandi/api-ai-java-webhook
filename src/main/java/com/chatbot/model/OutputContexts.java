package com.chatbot.model;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class OutputContexts {
    private String name;
    private int lifespanCount;
    private Parameters parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifespanCount() {
        return lifespanCount;
    }

    public void setLifespanCount(int lifespanCount) {
        this.lifespanCount = lifespanCount;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
