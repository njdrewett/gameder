package com.gameder.api;

import java.util.Date;
import java.util.Objects;

public class GamerCriteria {

    private String id;
    private String displayName;
    private Date dateOfBirth;
    private String emailAddress;
    private String telephoneNumber;
    private String excludeId;

    public GamerCriteria() {
        super();
    }

    public GamerCriteria(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber,
                         String excludeId) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.excludeId = excludeId;
    }

    public String getExcludeId() {
        return excludeId;
    }

    public void setExcludeId(String excludeId) {
        this.excludeId = excludeId;
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
        return "Gamer{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamerCriteria that = (GamerCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(displayName, that.displayName) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(telephoneNumber, that.telephoneNumber) && Objects.equals(excludeId, that.excludeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, dateOfBirth, emailAddress, telephoneNumber, excludeId);
    }
}
