package com.aluracursos.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesDeIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarError403() {
        return ResponseEntity.status(403).body("Acceso denegado");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarError401() {
        return ResponseEntity.status(401).body("Error de autenticación");
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
