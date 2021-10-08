package com.example.produitapi.models.form.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@Data
public class UserForm {

    @Length(min = 4, max = 20)
    @NotBlank
    private String username;
    @Length(min = 8)
    @NotBlank
    private String password;
    @Length(min = 5)
    private String email;
    @Length(min = 3, max = 5)
    private String moyenPayement;
    private List<String> roles;

}
