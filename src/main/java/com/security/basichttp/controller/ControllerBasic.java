package com.security.basichttp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBasic {

    @GetMapping("/hello")
    public String hello(){
        return "Hello, authenticated user!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Hello, Admin!";
    }
    @GetMapping("/super")
    public String superAdmin(){
        return "Hello Super Admin!";
    }

    @GetMapping("/welcome")
    public String welcomeCont(){
        return "This is Open API!";
    }

    @GetMapping("/public")
    public String publicM(){
        return "This is open API! anyone can access";
    }
}
