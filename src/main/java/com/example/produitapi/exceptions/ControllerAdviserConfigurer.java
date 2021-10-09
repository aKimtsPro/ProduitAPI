package com.example.produitapi.exceptions;


import org.springframework.beans.factory.InitializingBean;

public abstract class ControllerAdviserConfigurer implements InitializingBean {

    protected final ControllerAdviserConfiguration configuration;

    public ControllerAdviserConfigurer(ControllerAdviserConfiguration configuration) {
        this.configuration = configuration;
    }

    public abstract void configure() throws Exception;

    @Override
    public void afterPropertiesSet() throws Exception {
        configure();
    }
}
