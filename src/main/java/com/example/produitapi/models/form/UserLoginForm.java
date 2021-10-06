package com.example.produitapi.models.form;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;

@Data
public class UserLoginForm {

    private String username;
    private String password;

}
