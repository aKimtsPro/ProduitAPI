package com.example.produitapi.exceptions.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BadRequestHandler {

    @AliasFor("exception")
    Class<? extends Throwable>[] value() default {};
    @AliasFor("value")
    Class<? extends Throwable>[] exception() default {};

}
