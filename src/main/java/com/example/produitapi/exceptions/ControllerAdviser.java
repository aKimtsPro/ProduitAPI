package com.example.produitapi.exceptions;

import com.example.produitapi.exceptions.annotation.AdviserHandled;
import com.example.produitapi.exceptions.annotation.BadRequestHandler;
import com.example.produitapi.exceptions.models.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@ControllerAdvice
@BadRequestHandler(IllegalArgumentException.class) // priorité: @AdviserHandled, @BadRequestHandler, config
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    private final Set<BasicControllerExceptionHandler> handlings = new HashSet<>();

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDTO> handle(Throwable ex){ // TODO : Quid de la requete?

        AdviserHandled adviserHandled = ex.getClass().getAnnotation(AdviserHandled.class);
        if( adviserHandled != null )
            return ResponseEntity
                    .status(adviserHandled.value())
                    .body(ErrorDTO.of(ex));

        if( ex.getMessage() != null ){

            try{
                BadRequestHandler badRequestHandler = this.getClass().getAnnotation(BadRequestHandler.class);
                if( badRequestHandler != null ){
                    return Arrays.stream(badRequestHandler.value())
                            .filter((exClazz) -> exClazz.isAssignableFrom(ex.getClass()))
                            .findFirst()
                            .map( (exClazz) ->
                                    ResponseEntity
                                            .status(HttpStatus.BAD_REQUEST)
                                            .body(ErrorDTO.of(ex) ))
                            .orElseThrow();
                }

                return handlings.stream()
                        .filter(handling -> handling.getExceptionClazz().isAssignableFrom(ex.getClass()) )
                        .findFirst()
                        .map((h) ->
                                ResponseEntity
                                        .status( h.getStatus() )
                                        .body( ErrorDTO.of(ex) ))
                        .orElseThrow();

            }catch (NoSuchElementException ignored){}
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(new ErrorDTO("une erreur inconnue s'est produite"));
    }

    public void addHandling(BasicControllerExceptionHandler handling){
        handlings.add(handling);
    }
}
