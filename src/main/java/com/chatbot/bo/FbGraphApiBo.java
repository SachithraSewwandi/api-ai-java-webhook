package com.chatbot.bo;

import com.chatbot.rest.tx.FbGraphApiUser;

import java.io.IOException;

/**
 * Created by sewwandiwi on 6/5/2018.
 */
public interface FbGraphApiBo {
    FbGraphApiUser getFbUserName(String userId) throws IOException;
}
