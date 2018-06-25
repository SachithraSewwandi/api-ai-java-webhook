package com.chatbot.model.repository;

import com.chatbot.model.DBUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface UserRespository extends CrudRepository<DBUser, Long> {

    DBUser save(DBUser user);

    DBUser findBySessionId(String sessionId);
}
