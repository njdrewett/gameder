package com.gameder.api;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Gamer {

    private String id;
    private String displayName;
    private Date dateOfBirth;
    private String emailAddress;
    private String telephoneNumber;


    private byte[] profileImageData;
    private String profileImageContentType;
    private String introductionText;
    private String password;


    public Gamer() {
        super();
    }

    public Gamer(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber, byte[] profileImageData, String profileImageContentType, String introductionText, String password) {
        this(id,displayName,dateOfBirth,emailAddress,telephoneNumber,profileImageData, profileImageContentType, introductionText);
        this.password = password;
    }

    public Gamer(String id, String displayName, Date dateOfBirth, String emailAddress, String telephoneNumber, byte[] profileImageData, String profileImageContentType, String introductionText) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.profileImageData = profileImageData;
        this.profileImageContentType = profileImageContentType;
        this.introductionText = introductionText;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfileImageData() {
        return profileImageData;
    }

    public void setProfileImageData(byte[] profileImageData) {
        this.profileImageData = profileImageData;
    }

    public String getProfileImageContentType() {
        return profileImageContentType;
    }

    public void setProfileImageContentType(String profileImageContentType) {
        this.profileImageContentType = profileImageContentType;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", introductionText='" + introductionText + '\'' +
                '}';
    }
}
