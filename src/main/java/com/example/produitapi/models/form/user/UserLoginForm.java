package com.example.produitapi.models.form.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;

@Data
public class UserLoginForm {

    private String username;
    private String password;

}
