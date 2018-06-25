package com.chatbot.model;

import javax.persistence.*;

/**
 * Created by sewwandiwi on 6/22/2018.
 */
@Entity(name = "platform")
public class DBPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "platform_name")
    private String platformName;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
