package com.chatbot.model.repository;

import com.chatbot.model.DBPlatform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/22/2018.
 */
@Repository
public interface PlatformRepository  extends CrudRepository<DBPlatform, Long>{

    DBPlatform findByPlatformId(Long platformId);

    List<DBPlatform> findAll();
}
