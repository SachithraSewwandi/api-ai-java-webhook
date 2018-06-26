package com.chatbot.rest.tx;

import com.chatbot.rest.model.FBUser;

import java.util.List;

/**
 * Created by sewwandiwi on 6/26/2018.
 */
public class ListFbUserRs {
   List<FBUser> fbUserList;

    public List<FBUser> getFbUserList() {
        return fbUserList;
    }

    public void setFbUserList(List<FBUser> fbUserList) {
        this.fbUserList = fbUserList;
    }
}
