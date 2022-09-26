package com.gameder.service;

import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;

public interface SecurityService  {

    LoginResponse login(final LoginRequest loginRequest);

    SignupResponse signup(final SignupRequest signupRequest);

}
