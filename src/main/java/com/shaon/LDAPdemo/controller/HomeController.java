package com.shaon.LDAPdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/get")
    public String display(){
        return "this is ldap demo";
    }
}
