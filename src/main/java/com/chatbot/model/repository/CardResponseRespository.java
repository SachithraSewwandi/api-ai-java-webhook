package com.chatbot.model.repository;

import com.chatbot.model.DBCardResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface CardResponseRespository extends CrudRepository<DBCardResponse, Long> {

    DBCardResponse findByCardResponseId(Long id);

}
