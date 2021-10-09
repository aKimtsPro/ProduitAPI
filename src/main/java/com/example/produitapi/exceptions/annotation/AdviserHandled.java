package com.example.produitapi.exceptions.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Activates auto-handling by <code>ResponseEntityExceptionHandler</code> for targeted <code>Throwable</code>
 * <p>
 *
 * </p>
 * @see com.example.produitapi.exceptions.ControllerAdviser
 * @see SkippedProperty
 * @author alexandre kimtsaris
 * @version 0.1
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdviserHandled {

    @AliasFor("status")
    HttpStatus value() default HttpStatus.BAD_REQUEST;

    @AliasFor("value")
    HttpStatus status() default HttpStatus.BAD_REQUEST;

    Class<? extends Throwable> skipFrom() default RuntimeException.class;

}
