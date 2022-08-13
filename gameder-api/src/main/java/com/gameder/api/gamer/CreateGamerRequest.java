package com.gameder.api.gamer;

import java.util.Date;
import java.util.Objects;

public class CreateGamerRequest {

    private String displayName;
    private Date dateOfBirth;
    private String telephoneNumber;
    private byte[] profileImage;

    public CreateGamerRequest() {
    }

    public CreateGamerRequest(String displayName, Date dateOfBirth,  String emailAddress,String telephoneNumber, byte[] profileImage, String introductionText) {
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.profileImage = profileImage;
        this.introductionText = introductionText;
        this.emailAddress = emailAddress;
    }

    private String introductionText;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private String emailAddress;

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
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
        return "CreateGamerRequest{" +
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
