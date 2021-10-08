package com.example.produitapi.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeProduitDTO {

    private long id;
    private String nom;
    private Double prix;

}
