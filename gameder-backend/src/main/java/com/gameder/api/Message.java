package com.gameder.api;

import java.util.Date;

public class Message {

    private String id;
    private String messageText;
    private Date creationDate;
    private Date lastUpdatedDate;
    private String fromUserId;
    private String toUserId;


    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", messageText='" + messageText + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                '}';
    }

    public Message(String id, String messageText, Date creationDate, Date lastUpdatedDate, String fromUserId, String toUserId) {
        this.id = id;
        this.messageText = messageText;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
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
