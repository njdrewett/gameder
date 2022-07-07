package com.gameder.api.gamer;

import java.util.Date;
import java.util.Objects;

public class CreateGamerRequest {

    private String displayName;
    private Date dateOfBirth;

    public CreateGamerRequest() {
    }

    public CreateGamerRequest(final String displayName, final Date dateOfBirth) {
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
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
                ", displayName='" + displayName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGamerRequest that = (CreateGamerRequest) o;
        return displayName.equals(that.displayName) && dateOfBirth.equals(that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, dateOfBirth);
    }

}
