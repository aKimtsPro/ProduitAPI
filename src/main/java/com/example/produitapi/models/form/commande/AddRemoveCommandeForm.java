package com.example.produitapi.models.form.commande;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
@Data
public class AddRemoveCommandeForm {

    @Min(1)
    private long produitId;
    @Min(1)
    private Long qtt;

}
