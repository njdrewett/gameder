package com.gameder.api.gamer;

import java.util.Date;
import java.util.Objects;

public class UpdateGamerRequest {

    private String id;
    private String displayName;
    private Date dateOfBirth;

    public UpdateGamerRequest(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber, byte[] profileImage, String introductionText) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.profileImage = profileImage;
        this.introductionText = introductionText;
    }

    private String emailAddress;

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

    private String telephoneNumber;
    private byte[] profileImage;
    private String introductionText;

    public UpdateGamerRequest() {
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
        return "UpdateGamerRequest{" +
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
