package com.chatbot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
/*@Entity(name = "fbUser")*/
@Entity
@Table(name = "fb_user")
public class DBFbUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fb_user_id")
    private Long fbUserId;

    /*@Column(name = "user_id")
    private Long userId;*/

    @Column(name = "fb_id")
    private String fbId;

   @Column(name ="first_name")
    private String firstName;

   @Column(name = "last_name")
    private String lastName;

   @Column(name = "status")
   private String status;

   @Column(name = "image_url")
   private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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
