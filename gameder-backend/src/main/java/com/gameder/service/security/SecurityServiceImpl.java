package com.gameder.service.security;

import com.gameder.api.Gamer;
import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;
import com.gameder.domain.GamerEntity;
import com.gameder.repository.GamerRepository;
import com.gameder.service.GamerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SecurityServiceImpl extends SecurityServiceBase {

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    public SecurityServiceImpl(final GamerRepository gamerRepository,
                               final AuthenticationManager authenticationManager,
                               final JwtUtils jwtUtils,
                               final GamerService gamerService,
                               final PasswordEncoder encoder) {
        super(gamerRepository, authenticationManager,jwtUtils,gamerService,encoder);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public LoginResponse login(final LoginRequest loginRequest) {
        log.info("Entering login: {}",loginRequest);

        final LoginResponse loginResponse = new LoginResponse();

        try {
            final Authentication authentication = getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String jwt = getJwtUtils().generateJwtToken(authentication);
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            loginResponse.setJwToken(jwt);
            List<GamerEntity> gamers = getGamerRepository().findByEmailAddress(userDetails.getUsername());
            gamers.forEach(gamer -> loginResponse.setUserId(gamer.getId()));

            loginResponse.setLoginSuccessful(true);
        } catch(final BadCredentialsException badCredentialsException) {
            loginResponse.setErrorMessage("BAD_CREDENTIALS");
            loginResponse.setLoginSuccessful(false);
        }

        log.info("Exiting login: {}" , loginResponse);

        return loginResponse;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public SignupResponse signup(final SignupRequest signupRequest) {
        log.info("Entering signup: {}",signupRequest);

        final SignupResponse response = new SignupResponse();
        response.setSignupSuccessful(false);
        final List<GamerEntity> gamerEntities = getGamerRepository().findByEmailAddress(signupRequest.getEmailAddress());

        if(!gamerEntities.isEmpty()) {
            response.setErrorMessage("EMAIL_ALREADY_USED");
        } else {
            final Gamer gamer = new Gamer();
            gamer.setEmailAddress(signupRequest.getEmailAddress());
            gamer.setPassword(signupRequest.getPassword());
            final Gamer createdGamer = getGamerService().createGamer(gamer);
            final String jwt = getJwtUtils().generateJwtToken(createdGamer.getEmailAddress());
            response.setJwToken(jwt);

            response.setUserId(createdGamer.getId());
            response.setSignupSuccessful(true);
        }
        log.info("Exiting login: {}" , response);

        return response;
    }
}
