package com.gameder.api.message;

import java.util.Objects;

public class CreateMessageResponse {

    private String id;
    private boolean success;

    public CreateMessageResponse() {
    }

    public CreateMessageResponse(final String id, final boolean success) {
        this.id = id;
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "CreateGamerResponse{" +
                "id=" + id +
                ",success " + success +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMessageResponse that = (CreateMessageResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
