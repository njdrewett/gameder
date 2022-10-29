package com.gameder.api.message;

import java.util.Objects;

public class CreateMessageRequest {

    private String messageText;
    private String fromGamerId;
    private String toGamerId;

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

    public CreateMessageRequest() {
    }

    public CreateMessageRequest(final String messageText, final String fromGamerId, String toGamerId) {
        this.messageText = messageText;
        this.fromGamerId = fromGamerId;
        this.toGamerId = toGamerId;
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
                ", fromUserId='" + fromGamerId + '\'' +
                ", messageText='" + toGamerId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMessageRequest that = (CreateMessageRequest) o;
        return messageText.equals(that.messageText) && fromGamerId.equals(that.fromGamerId) && toGamerId.equals(that.toGamerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageText, fromGamerId, toGamerId);
    }

}
