package com.example.produitapi.models.form;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@Data
public class AdresseForm {

    @NotBlank
    private String rue;
    @NotBlank
    private String ville;
    @Min(1)
    private int numero;
    @Min(0)
    private int codePostal;
    @NotBlank
    private String pays;

}
