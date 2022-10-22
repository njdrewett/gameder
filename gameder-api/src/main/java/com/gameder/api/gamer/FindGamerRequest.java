package com.gameder.api.gamer;

import java.util.Date;
import java.util.Objects;

public class FindGamerRequest {

    private String id;
    private String displayName;
    private Date dateOfBirth;
    private String telephoneNumber;
    private String introductionText;
    private String emailAddress;
    private String excludeId;

    public FindGamerRequest(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber, String introductionText, String excludeId) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.introductionText = introductionText;
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

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public FindGamerRequest() {
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
        return "FindGamerRequest{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", introductionText='" + introductionText + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", excludeId='" + excludeId + '\'' +
                '}';
    }
}
