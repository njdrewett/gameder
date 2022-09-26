package com.gameder.api.security;

public class LoginResponse {

    private String jwToken;
    private String userId;
    private boolean loginSuccessful;
    private String errorMessage;

    public LoginResponse(){
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "jwToken='" + jwToken + '\'' +
                ", userId='" + userId + '\'' +
                ", loginSuccessful=" + loginSuccessful +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
