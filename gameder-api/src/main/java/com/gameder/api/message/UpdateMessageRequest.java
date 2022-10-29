package com.gameder.api.message;

import java.util.Objects;

public class UpdateMessageRequest {

    private String id;
    private String messageText;
    private String fromGamerId;
    private String toGamerId;

    public UpdateMessageRequest() {
    }

    public UpdateMessageRequest(final String id, final String messageText, final String fromGamerId, final String toGamerId) {
        this.id = id;
        this.messageText = messageText;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMessageRequest that = (UpdateMessageRequest) o;
        return id.equals(that.id) && messageText.equals(that.messageText) && fromGamerId.equals(that.fromGamerId) && toGamerId.equals(that.toGamerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageText, fromGamerId, toGamerId);
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

    @Override
    public String toString() {
        return "UpdateMessageRequest{" +
                "id='" + id + '\'' +
                ", messageText='" + messageText + '\'' +
                ", fromGamerId='" + fromGamerId + '\'' +
                ", toGamerId='" + toGamerId + '\'' +
                '}';
    }
}
