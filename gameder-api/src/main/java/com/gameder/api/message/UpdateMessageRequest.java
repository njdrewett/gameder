package com.gameder.api.message;

import java.util.Date;
import java.util.Objects;

public class UpdateMessageRequest {

    private String id;
    private String messageText;
    private String fromUserId;
    private String toUserId;

    public UpdateMessageRequest() {
    }

    public UpdateMessageRequest(final String id, final String messageText, final String fromUserId, final String toUserId) {
        this.id = id;
        this.messageText = messageText;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMessageRequest that = (UpdateMessageRequest) o;
        return id.equals(that.id) && messageText.equals(that.messageText) && fromUserId.equals(that.fromUserId) && toUserId.equals(that.toUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageText, fromUserId, toUserId);
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


}
