package com.example.produitapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public final class ControllerAdviserConfiguration { // TODO : revoir la facon dont la config et l'adviser travaillent ensemble

    private final ControllerAdviser controllerAdviser;

    public ControllerAdviserConfiguration(ControllerAdviser handler) {
        this.controllerAdviser = handler;
    }

    // attention a mettre les exceptions specifiques en prenmier puis les plus génériques
    public ControllerAdviserConfiguration addHandling(Class<? extends Throwable> exceptionClazz, HttpStatus status ){
        this.controllerAdviser.addHandling( BasicControllerExceptionHandler.forException(exceptionClazz).status(status) );
        return this;
    }

    public ControllerAdviserConfiguration addHandling(Class<? extends Throwable> exceptionClazz){
        this.controllerAdviser.addHandling( BasicControllerExceptionHandler.forException(exceptionClazz).badRequest() );
        return this;
    }

    public ControllerAdviserConfiguration addHandling(BasicControllerExceptionHandler handler){
        this.controllerAdviser.addHandling(handler);
        return this;
    }

}
