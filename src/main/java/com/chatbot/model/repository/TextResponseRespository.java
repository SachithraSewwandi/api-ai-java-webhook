package com.chatbot.model.repository;

import com.chatbot.model.DBTextResponse;
import com.chatbot.rest.model.Text;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface TextResponseRespository extends CrudRepository<DBTextResponse, Long> {

    DBTextResponse findByTextResponseId(Long id);
}
