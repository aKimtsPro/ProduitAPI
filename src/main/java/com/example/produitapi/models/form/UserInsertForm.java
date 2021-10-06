package com.example.produitapi.models.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class UserInsertForm {

    @Length(min = 4, max = 20)
    private String username;
    @Length(min = 8)
    private String password;
    private List<String> roles;

}
