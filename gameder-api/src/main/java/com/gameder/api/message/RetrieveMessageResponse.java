package com.gameder.api.message;

import java.util.Date;
import java.util.Objects;

public class RetrieveMessageResponse {

    private String id;
    private String messageText;
    private Date creationDate;

    public RetrieveMessageResponse() {
        super();

    }
    public RetrieveMessageResponse(final String id, final String messageText, final Date creationDate) {
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

    public String getmessageText() {
        return messageText;
    }

    public void setmessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getcreationDate() {
        return creationDate;
    }

    public void setcreationDate(Date creationDate) {
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
        RetrieveMessageResponse that = (RetrieveMessageResponse) o;
        return id.equals(that.id) && messageText.equals(that.messageText) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageText, creationDate);
    }


}
