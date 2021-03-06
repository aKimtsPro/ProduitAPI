package com.example.produitapi.controller;

import static com.example.produitapi.config.SecurityConstants.*;
import com.example.produitapi.models.dto.UserDTO;
import com.example.produitapi.models.form.user.UserLoginForm;
import com.example.produitapi.models.form.user.UserRegisterForm;
import com.example.produitapi.service.SessionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SessionController {

    private final SessionServiceImpl service;

    public SessionController(SessionServiceImpl service) {
        this.service = service;
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginForm form){
        UserDTO dto = service.signIn(form);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(REGISTER_URL)
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterForm form){
        UserDTO dto = service.signUp(form);
        return ResponseEntity.ok(dto);
    }

}
