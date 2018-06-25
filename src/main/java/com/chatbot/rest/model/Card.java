package com.chatbot.rest.model;

import java.util.List;

/**
 * Created by sewwandiwi on 6/17/2018.
 */
public class Card {

    private String title;
    private String subtitle;
    private String imageUri;
    private List<Buttons> buttons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public List<Buttons> getButtons() {
        return buttons;
    }

    public void setButtons(List<Buttons> buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
