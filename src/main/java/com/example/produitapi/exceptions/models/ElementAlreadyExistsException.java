package com.example.produitapi.exceptions.models;

import com.example.produitapi.exceptions.annotation.AdviserHandled;
import com.example.produitapi.exceptions.annotation.SkippedProperty;

@AdviserHandled
public class ElementAlreadyExistsException extends RuntimeException{

    @SkippedProperty
    private final String ok = "ok";

    public ElementAlreadyExistsException() {
        super("L'élément à insérer existe déjà.");
    }

    public String getOk() {
        return ok;
    }
}
