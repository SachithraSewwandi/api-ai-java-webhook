package com.chatbot.model.repository;

import com.chatbot.model.DBFbUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

/**
 * Created by sewwandiwi on 6/28/2018.
 */
public interface FBUserPagingRepository extends PagingAndSortingRepository<DBFbUser, Long>{

    @Query(value = "select e from fbUser e where firstName like :firstName ")
    Page<DBFbUser> find(@Param("firstName") String firstName,  Pageable p);

}
