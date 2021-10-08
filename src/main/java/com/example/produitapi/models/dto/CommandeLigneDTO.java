package com.example.produitapi.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeLigneDTO {

    private ProduitDTO produit;
    private int qtt;

}
