package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "intent")
public class DBIntent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "intent_id")
    private Long intentId;

    @Column(name = "dialogflow_intent_id")
    private String dialogflowIntentId;

    @Column(name = "display_name")
    private String displayName;

    public Long getIntentId() {
        return intentId;
    }

    public void setIntentId(Long intentId) {
        this.intentId = intentId;
    }

    public String getDialogflowIntentId() {
        return dialogflowIntentId;
    }

    public void setDialogflowIntentId(String dialogflowIntentId) {
        this.dialogflowIntentId = dialogflowIntentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
