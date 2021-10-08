package com.example.produitapi.exceptions.models;

import com.example.produitapi.exceptions.annotation.AdviserHandled;

@AdviserHandled
public class PriceNotGivenException extends RuntimeException{

    public PriceNotGivenException() {
        super("si un nouveau type de produit doit être créé, un prix doit être donné");
    }
}
