package com.anda.user_login.services.impl;

import com.anda.user_login.enities.User;
import com.anda.user_login.services.UserAuthenticationService;
import com.anda.user_login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Profile("uuid")
@Service
public class UUIDAuthenticationService implements UserAuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) throws BadCredentialsException {
        return userService.getByUsername(username)
                .filter(u -> u.getPassword().equals(passwordEncoder.encode(password)))
                .map(u -> {
                    u.setToken(UUID.randomUUID().toString());

                    userService.save(u);
                    return u.getToken();
                }).orElseThrow(() -> new BadCredentialsException("Invalid username or password."));

    }

    @Override
    public User authenticateByToken(String token) {
        return userService.getByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Token not found."));
    }

    @Override
    public void logout(String username) {
        userService.getByUsername(username)
                .ifPresent(u -> { u.setToken(null);
                userService.save(u);
                });

    }
}
