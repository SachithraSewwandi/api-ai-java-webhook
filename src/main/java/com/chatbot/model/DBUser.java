package com.chatbot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "user")
public class DBUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "session_started_time")
    private Date sessionStartedTime;

   @Column(name ="session_id")
    private String sessionId;

   @Column(name = "feed_back_number")
    private Long feedBackNumber;


    @Column(name = "platform_unique_user_id")
    private String platformUniqueUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Date getSessionStartedTime() {
        return sessionStartedTime;
    }

    public void setSessionStartedTime(Date sessionStartedTime) {
        this.sessionStartedTime = sessionStartedTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getFeedBackNumber() {
        return feedBackNumber;
    }

    public void setFeedBackNumber(Long feedBackNumber) {
        this.feedBackNumber = feedBackNumber;
    }

    public String getPlatformUniqueUserId() {
        return platformUniqueUserId;
    }

    public void setPlatformUniqueUserId(String platformUniqueUserId) {
        this.platformUniqueUserId = platformUniqueUserId;
    }
}
