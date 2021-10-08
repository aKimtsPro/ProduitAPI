package com.example.produitapi.exceptions.models;

public class PriceNotGivenException extends RuntimeException{

    public PriceNotGivenException() {
        super("si un nouveau type de produit doit être créé, un prix doit être donné");
    }
}
