package com.chatbot.bo.impl;

import com.chatbot.bo.PlatfromBo;
import com.chatbot.model.DBPlatform;
import com.chatbot.model.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
public class PlatformBoImpl implements PlatfromBo{

    @Autowired
    PlatformRepository platformRepository;

    @Override
    public List<DBPlatform> findAll() {
        return platformRepository.findAll();
    }
}
