package com.example.produitapi.config;

import com.example.produitapi.exceptions.ControllerAdviserConfiguration;
import com.example.produitapi.exceptions.ControllerAdviserConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ExceptionHandlingConfig extends ControllerAdviserConfigurer{

    public ExceptionHandlingConfig(ControllerAdviserConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void configure() throws Exception{
//        configuration.addHandling( IllegalArgumentException.class, HttpStatus.BAD_REQUEST );
    }
}
