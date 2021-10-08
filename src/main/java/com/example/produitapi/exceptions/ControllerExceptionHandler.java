package com.example.produitapi.exceptions;

import com.example.produitapi.exceptions.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handle(Throwable ex, HttpServletRequest request){

        if( ex.getMessage() != null ){
            if(ex instanceof ElementAlreadyExistsException){
                log.debug(ex.getClass().getSimpleName());
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST) // 400
                        .body(ErrorDTO.of(ex));
            }
            if(ex instanceof ElementNotFoundException){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND) // 404
                        .body(ErrorDTO.of(ex));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(new ErrorDTO("une erreur inconnu s'est produite"));
    }

}
