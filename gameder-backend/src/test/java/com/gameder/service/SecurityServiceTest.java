package com.gameder.service;

import com.gameder.api.Gamer;
import com.gameder.api.Message;
import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;
import com.gameder.domain.GamerEntity;
import com.gameder.domain.MessageEntity;
import com.gameder.repository.GamerRepository;
import com.gameder.repository.MessageRepository;
import com.gameder.service.message.MessageServiceImpl;
import com.gameder.service.security.JwtUtils;
import com.gameder.service.security.SecurityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Perform Security unit Test
 */
@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

   private final Logger log = LoggerFactory.getLogger(SecurityServiceTest.class);

   private SecurityService securityService;

   @Mock
   private GamerRepository gamerRepository;

   @Mock
   private AuthenticationManager authenticationManager;

   @Mock
   private JwtUtils jwtUtils;

   @Mock
   private GamerService gamerService;

   @Mock
   private PasswordEncoder encoder;

   @BeforeEach
   void setup() {
      log.info("setup");
      securityService = new SecurityServiceImpl(gamerRepository,
              authenticationManager,
              jwtUtils, gamerService,encoder
              );
   }

   @Test
   public void testLoginMissingUser() {
      log.info("testLoginMissingUser");

      LoginRequest loginRequest = new LoginRequest();
      loginRequest.setEmailAddress("missinguser");
      loginRequest.setPassword("anypassword");

      Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenThrow(new BadCredentialsException("no user"));

      final LoginResponse loginResponse = securityService.login(loginRequest);

      assertFalse(loginResponse.isLoginSuccessful());
      assertNotNull(loginResponse.getErrorMessage());
      assertNull(loginResponse.getJwToken());
      assertNull(loginResponse.getUserId());

      log.info("testLoginMissingUser {}", loginResponse );
   }

   @Test
   public void testLoginWrongPassword() {
      log.info("testLoginWrongPassword");

      Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenThrow(new BadCredentialsException("no user"));

      final LoginRequest loginRequest = new LoginRequest();
      loginRequest.setEmailAddress("gamer@gamer.com");
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

      final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
              "gamer@gamers.com", "password", true, true,
              true, true, new ArrayList<>());

      Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,"credentials");
      Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenReturn(authentication);
      Mockito.when(jwtUtils.generateJwtToken(authentication)).thenReturn("authenticationToken");

      GamerEntity gamerEntity = new GamerEntity();
      gamerEntity.setId("123");
      Mockito.when(gamerRepository.findByEmailAddress("gamer@gamers.com")).thenReturn(Collections.singletonList(gamerEntity));

      final LoginRequest loginRequest = new LoginRequest();
      loginRequest.setEmailAddress("gamer@gamers.com");
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
      final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
              "gamer@gamers.com", "password", true, true,
              true, true, new ArrayList<>());

      Mockito.when(gamerRepository.findByEmailAddress("newemail")).thenReturn(new ArrayList<>());

      Gamer gamer = new Gamer();
      gamer.setId("1");
      gamer.setEmailAddress(userDetails.getUsername());
      Mockito.when(gamerService.createGamer(Mockito.any(Gamer.class))).thenReturn(gamer);
      Mockito.when(jwtUtils.generateJwtToken("gamer@gamers.com")).thenReturn("authenticationToken");

      final SignupRequest signupRequest = new SignupRequest();
      signupRequest.setEmailAddress("newemail");
      signupRequest.setPassword("password");
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

      final SignupRequest signupRequest = new SignupRequest();
      signupRequest.setEmailAddress("gamer@gamer.com");
      signupRequest.setPassword("password");

      List<GamerEntity> gamers = new ArrayList<>();
      gamers.add(new GamerEntity());

      Mockito.when(gamerRepository.findByEmailAddress(signupRequest.getEmailAddress())).thenReturn(gamers);

      final SignupResponse signupResponse = securityService.signup(signupRequest);

      assertFalse(signupResponse.isSignupSuccessful());
      assertNotNull(signupResponse.getErrorMessage());
      assertNull(signupResponse.getJwToken());
      assertNull(signupResponse.getUserId());

      log.info("testSignupUserAlreadyExists {}", signupResponse);
   }

}
