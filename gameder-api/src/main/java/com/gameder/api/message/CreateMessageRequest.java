package com.gameder.api.message;

import java.util.Date;
import java.util.Objects;

public class CreateMessageRequest {

    private String messageText;
    private String fromUserId;
    private String toUserId;

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

    public CreateMessageRequest() {
    }

    public CreateMessageRequest(final String messageText, final String fromUserId, String toUserId) {
        this.messageText = messageText;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }


    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                ", messageText='" + messageText + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", messageText='" + toUserId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMessageRequest that = (CreateMessageRequest) o;
        return messageText.equals(that.messageText) && fromUserId.equals(that.fromUserId) && toUserId.equals(that.toUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageText, fromUserId, toUserId);
    }

}
