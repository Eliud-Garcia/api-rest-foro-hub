package com.alura.api_rest_foro.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import com.alura.api_rest_foro.domain.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorErrores {

    /*
    Todos los errores que tengan un EntityNotFoundException
    los va a mostrar como un NotFound 404
    */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> gestionarError404(EntityNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /*
    * Para validar las exception hechas con ValidacionException
    */
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<?> gestionarErrorDeValidacion(ValidacionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
