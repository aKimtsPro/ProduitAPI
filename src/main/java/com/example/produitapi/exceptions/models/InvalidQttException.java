package com.example.produitapi.exceptions.models;

import com.example.produitapi.exceptions.AdviserHandled;

@AdviserHandled
public class InvalidQttException extends RuntimeException{

    private final long produitQtt;
    private final long toRemoveQtt;

    public InvalidQttException(long produitQtt, long toRemoveQtt) {
        super("Il n'y a pas assez de produit de cette qtt {"+ produitQtt +"}");
        this.produitQtt = produitQtt;
        this.toRemoveQtt = toRemoveQtt;
    }

    public long getProduitQtt() {
        return produitQtt;
    }

    public long getToRemoveQtt() {
        return toRemoveQtt;
    }
}
