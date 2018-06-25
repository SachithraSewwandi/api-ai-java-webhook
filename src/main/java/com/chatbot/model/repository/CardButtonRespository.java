package com.chatbot.model.repository;

import com.chatbot.model.DBCardButtons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface CardButtonRespository extends CrudRepository<DBCardButtons, Long> {

    List<DBCardButtons> findByCardResponseId(Long id);
}
