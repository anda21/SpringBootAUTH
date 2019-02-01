package com.anda.user_login.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.awt.X11.XConstants;

import javax.xml.ws.Response;

@Service
public class FacebookService {

    @Value("${spring.social.facebook.appId}")
    String facebookAppId;
    @Value("${spring.social.facebook.appSecret}")
    String facebookSecret;



    RestTemplate restTemplate = new RestTemplate();

    public Test test(){
        ResponseEntity<Test> test = restTemplate.getForEntity("https://graph.facebook.com/search?type=place&fields=name,checkins,picture&q=tirane&access_token=559322264581539|c81f5bffe1bd2d2dc2df4608fea01b51",Test.class);
        System.out.println("string " +restTemplate);
        return test.getBody();
    }

}
