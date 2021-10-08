package com.example.produitapi.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String  username;

    private String email;
    private String moyenPayement;
    private AdresseDTO adresse;

    private String token;

    private List<String> roles;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


}
