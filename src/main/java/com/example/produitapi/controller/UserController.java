package com.example.produitapi.controller;

import com.example.produitapi.models.dto.UserDTO;
import com.example.produitapi.models.form.user.UserForm;
import com.example.produitapi.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDetailsServiceImpl service;

    public UserController(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public UserDTO insert(@RequestBody UserForm form){
        return service.insert(form);
    }


}
