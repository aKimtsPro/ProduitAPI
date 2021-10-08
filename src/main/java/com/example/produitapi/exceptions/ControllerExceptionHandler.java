package com.example.produitapi.exceptions;

import com.example.produitapi.exceptions.annotation.AdviserHandled;
import com.example.produitapi.exceptions.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handle(Throwable ex, HttpServletRequest request){ // TODO : Quid de la requete?

        AdviserHandled adviserHandled = ex.getClass().getAnnotation(AdviserHandled.class);
        if( adviserHandled != null )
            return ResponseEntity
                    .status(adviserHandled.value())
                    .body(ErrorDTO.of(ex));

        if( ex.getMessage() != null ){
            if(ex instanceof IllegalArgumentException){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorDTO.of(ex));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(new ErrorDTO("une erreur inconnu s'est produite"));
    }

}
