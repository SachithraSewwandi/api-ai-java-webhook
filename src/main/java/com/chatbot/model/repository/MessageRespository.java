package com.chatbot.model.repository;

import com.chatbot.model.DBMessage;
import com.chatbot.rest.model.IntentPrecentage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface MessageRespository extends CrudRepository<DBMessage, Long> {
    DBMessage save(DBMessage message);

    /*@Query(value = "SELECT a.intentId, b.displayName, COUNT(a) FROM Message a , Intent b WHERE a.intentId= b.intentId GROUP BY a.intentId", nativeQuery = true)
    List<IntentPrecentage> getIntentCount();*/
    Long countByIntentId(Long intentid);

    //Long countBymessageId();



}
