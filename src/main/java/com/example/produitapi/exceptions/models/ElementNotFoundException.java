package com.example.produitapi.exceptions.models;

import com.example.produitapi.exceptions.annotation.AdviserHandled;
import com.example.produitapi.exceptions.annotation.SkippedProperty;
import org.springframework.http.HttpStatus;

@AdviserHandled(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException{

    @SkippedProperty
    private final String ok = "ok";

    public ElementNotFoundException() {
        super("L'element recherché n'existe pas");
    }

    public String getOk() {
        return ok;
    }
}
