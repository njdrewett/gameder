package com.gameder.api;

import java.util.Date;

public class Gamer {

    private String id;
    private String displayName;
    private Date dateOfBirth;
    private String emailAddress;

    public Gamer(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber, byte[] profileImage, String introductionText) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.profileImage = profileImage;
        this.introductionText = introductionText;
    }

    private String telephoneNumber;
    private byte[] profileImage;
    private String introductionText;

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


    public Gamer() {
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

    public String toString() {

        return "Id: " + id +
                "DisplayName: " + displayName +
                "DateOfBirth: " + dateOfBirth;
    }

}
