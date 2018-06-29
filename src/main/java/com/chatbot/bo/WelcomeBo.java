package com.chatbot.bo;

import java.io.IOException;

/**
 * Created by sewwandiwi on 6/24/2018.
 */
public interface WelcomeBo {
    String greetingText(String sender)throws IOException;

    String getName(String sender) throws  IOException;
}
