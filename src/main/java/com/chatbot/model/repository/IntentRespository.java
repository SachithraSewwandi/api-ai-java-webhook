package com.chatbot.model.repository;

import com.chatbot.model.DBIntent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface IntentRespository extends CrudRepository<DBIntent, Long> {

    DBIntent findByDialogflowIntentId(String dialogflowId);
}
