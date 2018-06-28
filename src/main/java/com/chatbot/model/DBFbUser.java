package com.chatbot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "fbUser")
@Table(name = "fbUser")
public class DBFbUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fbUserId")
    private Long fbUserId;

    /*@Column(name = "user_id")
    private Long userId;*/

    @Column(name = "fbId")
    private String fbId;

   @Column(name ="firstName")
    private String firstName;

   @Column(name = "lastName")
    private String lastName;

   @Column(name = "status")
   private String status;

    public Long getFbUserId() {
        return fbUserId;
    }

    public void setFbUserId(Long fbUserId) {
        this.fbUserId = fbUserId;
    }

  /*  public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
