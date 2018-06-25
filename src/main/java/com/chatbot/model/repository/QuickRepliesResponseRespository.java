package com.chatbot.model.repository;

import com.chatbot.model.DBQuickRepliesResponse;
import com.chatbot.rest.model.QuickReplies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface QuickRepliesResponseRespository extends CrudRepository<DBQuickRepliesResponse, Long> {

    DBQuickRepliesResponse findByQuickRepliesId(Long id);

}
