package com.chatbot.model.repository;

import com.chatbot.model.DBCardButtons;
import com.chatbot.model.DBMessageResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface MessageResponseRespository extends CrudRepository<DBMessageResponse, Long> {

    //List<DBCardButtons> findByCardResponseId(Long id);

    DBMessageResponse save(DBMessageResponse messageResponse);
}
