package com.gameder.api.gamer;

import java.util.Date;
import java.util.Objects;

public class RetrieveGamerResponse {

    private String id;
    private String displayName;
    private Date dateOfBirth;

    private String emailAddress;
    private String telephoneNumber;
    private String introductionText;

    public RetrieveGamerResponse(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber, String introductionText) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.introductionText = introductionText;
    }


    public RetrieveGamerResponse() {
        super();

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    @Override
    public String toString() {
        return "RetrieveGamerResponse{" +
                "id='" + id + "'" +
                ", displayName='" + displayName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveGamerResponse that = (RetrieveGamerResponse) o;
        return id.equals(that.id) && displayName.equals(that.displayName) && dateOfBirth.equals(that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, dateOfBirth);
    }
}
