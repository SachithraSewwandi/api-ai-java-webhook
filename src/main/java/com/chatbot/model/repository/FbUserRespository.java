package com.chatbot.model.repository;

import com.chatbot.model.DBFbUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface FbUserRespository extends CrudRepository<DBFbUser, Long> {

    DBFbUser save(DBFbUser fbUser);
}
