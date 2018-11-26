package com.anda.user_login.security.auth;

import com.anda.user_login.services.UserAuthenticationService;
import com.anda.user_login.services.impl.UUIDAuthenticationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class TokenAuthenticationProvider  extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object token = usernamePasswordAuthenticationToken.getCredentials();

        return Optional
                .ofNullable(token)
                .flatMap(t ->
                        Optional.of(userAuthenticationService.authenticateByToken(String.valueOf(t)))
                                .map(u -> User.builder()
                                        .username(u.getUsername())
                                        .password(u.getPassword())
                                        .roles("user")
                                        .build()))
                .orElseThrow(() -> new BadCredentialsException("Invalid authentication token=" + token));
    }
}
