package com.anda.user_login.services.impl;

import com.anda.user_login.enities.User;
import com.anda.user_login.services.JWTService;
import com.anda.user_login.services.TokenVerificationException;
import com.anda.user_login.services.UserAuthenticationService;
import com.anda.user_login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Profile("jwt")
@Service
public class JWTAuthenticationService  implements UserAuthenticationService {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    @Override
    public String login(String username, String password) throws BadCredentialsException {
        return userService
                .getByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> jwtService.create(username))
                .orElseThrow(() -> new BadCredentialsException("Invalid Username or password."));
    }

    @Override
    public User authenticateByToken(String token) {
        try{
            Object username = jwtService.verify(token).get("username");

            return Optional.ofNullable(username)
                    .flatMap(name -> userService.getByUsername(String.valueOf(name)))
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found."));
        }catch (TokenVerificationException e) {
            throw new BadCredentialsException("Invalid JWT token.", e);
        }
    }

    @Override
    public void logout(String username) {

    }
}
