package com.gameder.api.security;

public class SignupResponse {

    private String jwToken;
    private String userId;
    private String errorMessage;
    private boolean signupSuccessful;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public SignupResponse(){
        super();
    }

    public String getJwToken() {
        return jwToken;
    }

    public void setJwToken(String jwToken) {
        this.jwToken = jwToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isSignupSuccessful() {
        return signupSuccessful;
    }

    public void setSignupSuccessful(boolean signupSuccessful) {
        this.signupSuccessful = signupSuccessful;
    }

    @Override
    public String toString() {
        return "SignupResponse{" +
                "jwToken='" + jwToken + '\'' +
                ", userId='" + userId + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", signupSuccessful=" + signupSuccessful +
                '}';
    }
}
