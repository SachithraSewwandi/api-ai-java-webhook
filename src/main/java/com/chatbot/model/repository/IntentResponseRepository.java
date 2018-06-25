package com.chatbot.model.repository;

import com.chatbot.model.DBIntentResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface IntentResponseRepository extends CrudRepository<DBIntentResponse, Long> {

    List<DBIntentResponse> findByIntentId(Long intentId);

}
