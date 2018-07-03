package com.chatbot.model.repository;

import com.chatbot.model.DBMessage;
import com.chatbot.rest.model.IntentCount;
import com.chatbot.rest.model.IntentPrecentage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    Long countByPlatformUserId(String platformUserId);

    List<DBMessage> findBySessionId(String sessionId);

    @Query(value = "select m.intent_Id, count(m.intent_Id) from message m where m.platform_user_id like :platformUserId group by m.intent_id", nativeQuery = true)
    @SuppressWarnings("unchecked")
    List<Object[]> intentCountforUser(@Param("platformUserId") String platformUserId);

    //Long countBymessageId();



}