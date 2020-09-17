package com.foodella.driver.features.chatDetails.adapter;

import java.io.Serializable;

public class Conversation implements Serializable {

    private String message = "";
    private String timestamp = "";
    private String image = "";

    public static final int SENT = 1;
    public static final int RECEIVED = 2;
    private int type;

    public Conversation() {
    }

    public Conversation(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static int getSENT() {
        return SENT;
    }

    public static int getRECEIVED() {
        return RECEIVED;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
