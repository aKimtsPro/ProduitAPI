package com.example.produitapi.models.form.commande;


import com.example.produitapi.models.form.AdresseForm;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CommandeForm {

    private long userId;
    private AdresseForm adresse;

}
