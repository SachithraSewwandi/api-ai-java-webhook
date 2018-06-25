package com.chatbot.bo.impl;

import com.chatbot.bo.UserBo;
import com.chatbot.model.DBUser;
import com.chatbot.model.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sewwandiwi on 6/23/2018.
 */

public class UserBoImpl implements UserBo {

    @Autowired
    UserRespository userRespository;


    @Override
    public Boolean isNewUser(String sessionId) {

        Boolean newuser=true;
        DBUser user=userRespository.findBySessionId(sessionId);
        if (!user.equals(null)){
            newuser=false;
        }

        return newuser;
    }
}
