package com.alura.api_rest_foro.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import com.alura.api_rest_foro.domain.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    /*
     * Para mostrar errores en la validacion de campos cuando se usa @Valid
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex){
        List<FieldError> errores = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errores.stream()
                .map(DatosErrorValidacion::new).toList());
    }


    /*
     * Este record captura solo el campo y mensaje del error
     * para mostrarlo en el body de la respuesta HTTP de una forma
     * mas resumida y comprensible
     * */
    public record DatosErrorValidacion(String campo,
                                       String mensaje
    ){

        public DatosErrorValidacion(FieldError error){
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }
}
