package com.example.produitapi.exceptions;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Activates auto-handling by <code>ControllerExceptionHandler</code> for targeted <code>Throwable</code>
 * <p>
 *
 * </p>
 * @see ControllerExceptionHandler
 * @see SkippedProperty
 * @author alexandre kimtsaris
 * @version 0.1
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdviserHandled {

    @AliasFor("status")
    public HttpStatus value() default HttpStatus.BAD_REQUEST;

    @AliasFor("value")
    public HttpStatus status() default HttpStatus.BAD_REQUEST;

    public Class<? extends Throwable> skipFrom() default RuntimeException.class;

}
