package com.gameder.api.message;

import java.util.Date;
import java.util.Objects;

public class CreateMessageRequest {

    private String messageText;
    private Date creationDate;

    public CreateMessageRequest() {
    }

    public CreateMessageRequest(final String messageText, final Date creationDate) {
        this.messageText = messageText;
        this.creationDate = creationDate;
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

    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                ", messageText='" + messageText + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMessageRequest that = (CreateMessageRequest) o;
        return messageText.equals(that.messageText) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageText, creationDate);
    }

}
