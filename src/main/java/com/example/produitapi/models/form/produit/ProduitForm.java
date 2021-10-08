package com.example.produitapi.models.form.produit;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Data
public class ProduitForm {

    @NotBlank
    private String nom;
    @NotBlank
    private String nomType;
    @DecimalMin(value = "0", inclusive = false)
    private Double prix;

}
