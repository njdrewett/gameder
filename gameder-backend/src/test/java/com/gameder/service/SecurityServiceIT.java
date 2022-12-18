package com.gameder.service;


import com.gameder.api.Gamer;
import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class SecurityServiceIT {

    private final Logger log = LoggerFactory.getLogger(SecurityServiceIT.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GamerService gamerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLoginMissingUser() {
        log.info("testLoginMissingUser");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress("missinguser");
        loginRequest.setPassword("anypassword");
        LoginResponse loginResponse = securityService.login(loginRequest);

        assertFalse(loginResponse.isLoginSuccessful());
        assertNotNull(loginResponse.getErrorMessage());
        assertNull(loginResponse.getJwToken());
        assertNull(loginResponse.getUserId());


        log.info("testCreateGamerResponse {}", loginResponse );
    }

    @Test
    public void testLoginWrongPassword() {
        log.info("testLoginWrongPassword");

        Gamer gamer = createGamer();

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress(gamer.getEmailAddress());
        loginRequest.setPassword("wrongpassword");
        final LoginResponse loginResponse = securityService.login(loginRequest);

        assertFalse(loginResponse.isLoginSuccessful());
        assertNotNull(loginResponse.getErrorMessage());
        assertNull(loginResponse.getJwToken());
        assertNull(loginResponse.getUserId());

        log.info("testLoginWrongPassword {}", loginResponse );
    }

    @Test
    public void testLoginSuccess() {
        log.info("testLoginSuccess");

        final Gamer gamer = createGamer();

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress(gamer.getEmailAddress());
        loginRequest.setPassword("password");
        final LoginResponse loginResponse = securityService.login(loginRequest);

        assertTrue(loginResponse.isLoginSuccessful());
        assertNull(loginResponse.getErrorMessage());
        assertNotNull(loginResponse.getJwToken());
        assertNotNull(loginResponse.getUserId());

        log.info("testLoginSuccess {}", loginResponse );
    }

    @Test
    public void testSignupSuccess() {
        log.info("testSignupSuccess");

        final SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmailAddress("newemail");
        signupRequest.setPassword(passwordEncoder.encode("password"));
        final SignupResponse signupResponse = securityService.signup(signupRequest);

        assertTrue(signupResponse.isSignupSuccessful());
        assertNull(signupResponse.getErrorMessage());
        assertNotNull(signupResponse.getJwToken());
        assertNotNull(signupResponse.getUserId());

        log.info("testSignupSuccess {}", signupResponse);
    }

    @Test
    public void testSignupUserAlreadyExists() {
        log.info("testSignupUserAlreadyExists");

        final Gamer gamer = createGamer();

        final SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmailAddress(gamer.getEmailAddress());
        signupRequest.setPassword("password");
        final SignupResponse signupResponse = securityService.signup(signupRequest);

        assertFalse(signupResponse.isSignupSuccessful());
        assertNotNull(signupResponse.getErrorMessage());
        assertNull(signupResponse.getJwToken());
        assertNull(signupResponse.getUserId());

        log.info("testSignupUserAlreadyExists {}", signupResponse);
    }

    private Gamer createGamer() {

        final String encryptedPassword = passwordEncoder.encode("password");
        final Gamer gamer = new Gamer(null,"NewGamer", new Date(1656366879731L),
                "gamer@gamers.com", "019191999991911", null, "jpg", "Hi Im a gamer", encryptedPassword);

        final Gamer returnedGamer = gamerService.createGamer(gamer);

        assertNotNull(returnedGamer.getId());
        assertEquals(returnedGamer.getDateOfBirth(), gamer.getDateOfBirth());
        assertEquals(returnedGamer.getDisplayName(), gamer.getDisplayName());
        assertEquals(returnedGamer.getEmailAddress(), gamer.getEmailAddress());
        assertEquals(returnedGamer.getIntroductionText(), gamer.getIntroductionText());
        assertEquals(returnedGamer.getTelephoneNumber(), gamer.getTelephoneNumber());
        assertEquals(returnedGamer.getPassword(), gamer.getPassword());

        return returnedGamer;
    }

}
