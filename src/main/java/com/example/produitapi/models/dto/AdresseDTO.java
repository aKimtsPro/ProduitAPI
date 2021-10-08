package com.example.produitapi.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDTO {

    private String rue;
    private String ville;
    private int numero;
    private int codePostal;
    private String pays;

}
