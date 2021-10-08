package com.example.produitapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SecurityTestController {

    @GetMapping("/admin")
    public void admin(){

    }

    @GetMapping("/user")
    public void user(){

    }

    @GetMapping("/auth")
    public void auth(){

    }

    @GetMapping("permit")
    public void permitAll(){

    }

}
