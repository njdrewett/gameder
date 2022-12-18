package com.gameder.service.security;

import com.gameder.repository.GamerRepository;
import com.gameder.service.GamerService;
import com.gameder.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class SecurityServiceBase implements SecurityService {

    private final GamerRepository gamerRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final GamerService gamerService;

    private final PasswordEncoder encoder;

    @Autowired
    SecurityServiceBase(final GamerRepository gamerRepository,
                        final AuthenticationManager authenticationManager,
                        final JwtUtils jwtUtils,
                        final GamerService gamerService,
                        final PasswordEncoder encoder
    ) {
        this.gamerRepository = gamerRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.gamerService = gamerService;
        this.encoder = encoder;
    }

    public GamerRepository getGamerRepository() {
        return gamerRepository;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public JwtUtils getJwtUtils() {
        return jwtUtils;
    }

    public GamerService getGamerService() {
        return gamerService;
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }
}
