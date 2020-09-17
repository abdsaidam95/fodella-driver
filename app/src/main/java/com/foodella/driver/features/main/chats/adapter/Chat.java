package com.foodella.driver.features.main.chats.adapter;

import java.io.Serializable;

public class Chat implements Serializable {

    private String userName  ="" ;
    private String userImage = "";
    private String dateTime = "";

    public Chat() {
    }

    public Chat(String userName, String userImage, String dateTime) {
        this.userName = userName;
        this.userImage = userImage;
        this.dateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}


