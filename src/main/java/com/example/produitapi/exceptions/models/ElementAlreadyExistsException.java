package com.example.produitapi.exceptions.models;

public class ElementAlreadyExistsException extends RuntimeException{

    private final String ok = "ok";

    public ElementAlreadyExistsException() {
        super("L'élément à insérer existe déjà.");
    }

    public String getOk() {
        return ok;
    }
}
