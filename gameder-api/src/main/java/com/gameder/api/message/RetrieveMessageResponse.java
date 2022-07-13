package com.gameder.api.message;

import java.util.Date;
import java.util.Objects;

public class RetrieveMessageResponse {

    private String id;
    private String messageText;
    private Date creationDate;
    private Date lastUpdatedDate;
    private String fromUserId;
    private String toUserId;

    public RetrieveMessageResponse(String id, String messageText, Date creationDate, Date lastUpdatedDate, String fromUserId, String toUserId) {
        this.id = id;
        this.messageText = messageText;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveMessageResponse that = (RetrieveMessageResponse) o;
        return id.equals(that.id) && messageText.equals(that.messageText) && creationDate.equals(that.creationDate)
                && lastUpdatedDate.equals(that.lastUpdatedDate) && fromUserId.equals(that.fromUserId)
                && toUserId.equals(that.toUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageText, creationDate, lastUpdatedDate, fromUserId, toUserId);
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

    public RetrieveMessageResponse() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(final String messageText) {
        this.messageText = messageText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

}
