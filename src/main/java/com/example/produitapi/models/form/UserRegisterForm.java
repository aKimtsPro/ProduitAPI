package com.example.produitapi.models.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class UserRegisterForm {

    @Length(min = 4, max = 20)
    private String username;
    @Length(min=8)
    private String password;
}
