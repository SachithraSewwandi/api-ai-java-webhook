package com.chatbot.bo;

import com.chatbot.model.DBPlatform;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
public interface PlatfromBo {

    List<DBPlatform> findAll();
}
