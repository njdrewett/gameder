package com.gameder.api.game;

import java.util.Objects;

public class UpdateGameResponse {

    private String id;
    private boolean success;

    public UpdateGameResponse() {
    }

    public UpdateGameResponse(final String id, final boolean success) {
        this.id = id;
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UpdateGameResponse{" +
                "id=" + id +
                ",success " + success +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGameResponse that = (UpdateGameResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
