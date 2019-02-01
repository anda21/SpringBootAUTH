package com.anda.user_login.controller;

import com.anda.user_login.facebook.FacebookService;
import com.anda.user_login.facebook.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {

    @Autowired
    FacebookService facebookService;

    @GetMapping("/getNamePlaceDetails")
    public Test getNamePlaceDetails(){
        return facebookService.test();

    }



}
