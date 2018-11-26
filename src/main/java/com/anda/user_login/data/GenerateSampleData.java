package com.anda.user_login.data;

import com.anda.user_login.enities.User;
import com.anda.user_login.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Configuration
@RequiredArgsConstructor
public class GenerateSampleData implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(userRepository.count() == 0) {
            User user = new User();

            user.setBirthday(new Date());
            user.setUsername("test");
            user.setToken(null);
            user.setPassword(passwordEncoder.encode("test"));
            user.setEnable(true);
            user.setLastName("lastName");
            user.setName("name");
            userRepository.save(user);
        }else{
            System.out.println("I found Data into Database :P !");
        }

    }
}
