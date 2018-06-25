package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "quick_replies_response")
public class DBQuickRepliesResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quick_replies_id")
    private Long quickRepliesId;

    @Column(name = "title")
    private String title;

    public Long getQuickRepliesId() {
        return quickRepliesId;
    }

    public void setQuickRepliesId(Long quickRepliesId) {
        this.quickRepliesId = quickRepliesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
