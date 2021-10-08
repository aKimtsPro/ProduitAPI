package com.example.produitapi.models.dto;

import com.example.produitapi.models.entity.Adresse;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class SmallUserDTO {

    private String username;
    private String email;
    private String moyenPayement;
    private AdresseDTO adresse;

}
