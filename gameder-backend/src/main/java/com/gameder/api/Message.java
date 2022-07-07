package com.gameder.api;

import java.util.Date;

public class Message {

    private String id;
    private String messageText;
    private Date creationDate;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Message() {
        super();
    }

    public Message(String id, String messageText, Date creationDate) {
        this.id = id;
        this.messageText = messageText;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id: " + id);
        builder.append("messageText: " + messageText);
        builder.append("creationDate: " + creationDate);

        return builder.toString();
    }

}
