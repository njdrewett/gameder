package com.gameder.controller.security;

import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;
import org.springframework.http.ResponseEntity;

public interface SecurityController {

    ResponseEntity<LoginResponse> login(final LoginRequest loginRequest);

    ResponseEntity<SignupResponse> signup(final SignupRequest signupRequest);

}
