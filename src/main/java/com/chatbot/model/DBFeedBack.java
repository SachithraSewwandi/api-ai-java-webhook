package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "feed_back")
public class DBFeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feed_back_id")
    private Long feedBackId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "feedback_number")
    private Long feedbackNumber;

    @Column(name = "comments")
    private String comments;

    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

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

    public Long getFeedbackNumber() {
        return feedbackNumber;
    }

    public void setFeedbackNumber(Long feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
