package com.gameder.api.message;

import java.util.Date;
import java.util.Objects;

public class UpdateMessageRequest {

    private String id;
    private String messageText;
    private Date creationDate;

    public UpdateMessageRequest() {
    }

    public UpdateMessageRequest(String id, String messageText, Date creationDate) {
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


    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "id='"+ id + "'" +
                ", messageText='" + messageText + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMessageRequest that = (UpdateMessageRequest) o;
        return id.equals(that.id) && messageText.equals(that.messageText) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageText, creationDate);
    }

}
