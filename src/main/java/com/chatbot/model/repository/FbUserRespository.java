package com.chatbot.model.repository;

import com.chatbot.model.DBFbUser;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Repository
public interface FbUserRespository extends CrudRepository<DBFbUser, Long> {

    DBFbUser save(DBFbUser fbUser);

    //Boolean existsByFbId(String fbId);

    DBFbUser findByFbId(String fbId);

    List<DBFbUser> findAll();

    List<DBFbUser> findByFirstName(String name);

    List<DBFbUser> findByLastName(String name);


    /*@Query(value = "select f from fb_user f where f.firstName like :firstName and f.lastName like :firstName", nativeQuery = true)
    List<DBFbUser> findByFirstNameAndLastName(@Param("firstName") String firstName);*/

    List<DBFbUser> findByFirstNameContaining(String firstName);

}
