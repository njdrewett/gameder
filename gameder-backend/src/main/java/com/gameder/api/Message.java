package com.gameder.api;

import java.util.Date;

public class Message {

    private String id;
    private String messageText;
    private Date creationDate;
    private Date lastUpdatedDate;
    private String fromGamerId;
    private String toGamerId;

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", messageText='" + messageText + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", fromGamerId='" + fromGamerId + '\'' +
                ", toGamerId='" + toGamerId + '\'' +
                '}';
    }

    public Message(String id, String messageText, Date creationDate, Date lastUpdatedDate, String fromGamerId, String toGamerId) {
        this.id = id;
        this.messageText = messageText;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.fromGamerId = fromGamerId;
        this.toGamerId = toGamerId;
    }

    public String getFromGamerId() {
        return fromGamerId;
    }

    public void setFromGamerId(String fromGamerId) {
        this.fromGamerId = fromGamerId;
    }

    public String getToGamerId() {
        return toGamerId;
    }

    public void setToGamerId(String toGamerId) {
        this.toGamerId = toGamerId;
    }


    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
