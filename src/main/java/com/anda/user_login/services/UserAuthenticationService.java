package com.anda.user_login.services;



import com.anda.user_login.enities.User;
import org.springframework.security.authentication.BadCredentialsException;


public interface UserAuthenticationService {
    String login(String username, String password) throws BadCredentialsException;

    User authenticateByToken(String token);

    void logout(String username);
}
