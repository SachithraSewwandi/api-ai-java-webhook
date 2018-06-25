package com.chatbot.model.repository;

import com.chatbot.model.DBQuickRepliesButtons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface QuickRepliesButtonRespository extends CrudRepository<DBQuickRepliesButtons, Long> {

    List<DBQuickRepliesButtons> findByQuickReplyResponseId(Long id);

}
