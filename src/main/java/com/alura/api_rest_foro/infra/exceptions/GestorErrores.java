package com.alura.api_rest_foro.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import com.alura.api_rest_foro.domain.ValidacionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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
     * Para manejar errores cuando se envia un parametro en la URL
     * de tipo incorrecto (por ejemplo: enviar letras cuando se espera un numero en el ID)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> gestionarErrorTipoParametro() {
        return ResponseEntity.badRequest().body("El ID proporcionado no es válido. Debe ser un número.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionarError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity gestionarErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity gestionarErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falla en la autenticación");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity gestionarErrorAccesoDenegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
    }

    /*Error en el servidor*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity gestionarError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " +ex.getLocalizedMessage());
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
