package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/23/2018.
 */
@Entity(name = "quick_reply_buttons")
public class DBQuickRepliesButtons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quick_replies_button_id")
    private Long quickRepliesButtonId;

    @Column(name = "quick_reply_response_id")
    private String quickReplyResponseId;

    @Column(name = "quick_reply_title")
    private String quickReplyTitle;

    public Long getQuickRepliesButtonId() {
        return quickRepliesButtonId;
    }

    public void setQuickRepliesButtonId(Long quickRepliesButtonId) {
        this.quickRepliesButtonId = quickRepliesButtonId;
    }

    public String getQuickReplyResponseId() {
        return quickReplyResponseId;
    }

    public void setQuickReplyResponseId(String quickReplyResponseId) {
        this.quickReplyResponseId = quickReplyResponseId;
    }

    public String getQuickReplyTitle() {
        return quickReplyTitle;
    }

    public void setQuickReplyTitle(String quickReplyTitle) {
        this.quickReplyTitle = quickReplyTitle;
    }
}
