package com.gameder.service.security;

import com.gameder.api.Gamer;
import com.gameder.api.security.LoginRequest;
import com.gameder.api.security.LoginResponse;
import com.gameder.api.security.SignupRequest;
import com.gameder.api.security.SignupResponse;
import com.gameder.domain.GamerEntity;
import com.gameder.repository.GamerRepository;
import com.gameder.service.GamerService;
import com.gameder.service.gamer.GamerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    final GamerRepository gamerRepository;

    @Autowired
    public UserDetailServiceImpl(final GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String emailAddress) throws UsernameNotFoundException {
        log.info("Entering loadUserByUsername: {}",emailAddress);
        final List<GamerEntity> gamerEntities = gamerRepository.findByEmailAddress(emailAddress);

        if(gamerEntities.isEmpty()) {
            throw new UsernameNotFoundException(emailAddress);
        }
        GamerEntity gamer = gamerEntities.stream().findAny().orElseThrow(() -> new UsernameNotFoundException(emailAddress));
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                gamer.getEmailAddress(), gamer.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities());

        log.info("Entering loadUserByUsername: {}",emailAddress);

        return userDetails;
    }

    private static List<GrantedAuthority> getAuthorities () {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("gamer"));
        return authorities;
    }
}
