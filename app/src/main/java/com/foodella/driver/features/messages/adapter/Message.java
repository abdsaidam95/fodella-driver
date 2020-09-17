package com.foodella.driver.features.messages.adapter;

import java.io.Serializable;

public class Message implements Serializable {


    private String messageTitle = "";
    private String messageDate = "";

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    private String messageBody = "";

    public Message() {
    }

    public Message(String messageTitle, String messageDate) {
        this.messageTitle = messageTitle;
        this.messageDate = messageDate;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
}
