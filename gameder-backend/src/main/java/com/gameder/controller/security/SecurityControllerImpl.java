package com.gameder.controller.security;

import com.gameder.api.gamer.CreateGamerRequest;
import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;
import com.gameder.controller.gamer.GamerControllerImpl;
import com.gameder.service.GamerService;
import com.gameder.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
public class SecurityControllerImpl implements SecurityController {
    private static final Logger log = LoggerFactory.getLogger(SecurityControllerImpl.class);

    private final SecurityService securityService;

    @Autowired
    SecurityControllerImpl(final SecurityService securityService) {
        this.securityService = securityService;
    }


    @PostMapping(path="/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        log.info("login:{}", loginRequest);

        final LoginResponse loginSuccess = securityService.login(loginRequest);

        log.info("login {}" , loginSuccess);

        return new ResponseEntity<>(loginSuccess, HttpStatus.OK);
    }

    @PostMapping(path="/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody final SignupRequest signupRequest) {
        log.info("signup:{}", signupRequest);

        final SignupResponse signupResponse = securityService.signup(signupRequest);

        log.info("signup {}" , signupResponse);

        return new ResponseEntity<>(signupResponse, HttpStatus.OK);
    }

}
