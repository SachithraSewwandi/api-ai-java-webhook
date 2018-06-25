package com.chatbot.model.repository;

import com.chatbot.model.DBResponseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface ResponseTypeRespository extends CrudRepository<DBResponseType, Long> {

}
