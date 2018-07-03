package com.chatbot.rest.model;

/**
 * Created by sewwandiwi on 6/26/2018.
 */
public class FBUser {

    private Long fbId;
    private String firstName;
    private String lastName;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
