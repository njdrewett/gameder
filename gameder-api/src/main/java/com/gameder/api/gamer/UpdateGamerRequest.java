package com.gameder.api.gamer;

import java.util.Date;
import java.util.Objects;

public class UpdateGamerRequest {

    private String id;
    private String displayName;
    private Date dateOfBirth;

    public UpdateGamerRequest() {
    }

    public UpdateGamerRequest(String id, String displayName, Date dateOfBirth) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "id='"+ id + "'" +
                ", displayName='" + displayName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGamerRequest that = (UpdateGamerRequest) o;
        return id.equals(that.id) && displayName.equals(that.displayName) && dateOfBirth.equals(that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, dateOfBirth);
    }

}
