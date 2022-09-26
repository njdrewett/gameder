package com.gameder.api.security;

public class SignupRequest {

    private String emailAddress;
    private String password;

    public SignupRequest() {
        super();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
