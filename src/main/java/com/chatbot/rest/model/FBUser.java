package com.chatbot.rest.model;

/**
 * Created by sewwandiwi on 6/26/2018.
 */
public class FBUser {

    private Long fbId;
    private String firstName;

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
