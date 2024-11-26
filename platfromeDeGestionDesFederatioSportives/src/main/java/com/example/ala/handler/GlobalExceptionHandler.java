package com.example.ala.handler;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handler(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .errorCode(BusinessError.ENTITY_NOTFOUND.getCode())
                        .errorDescription(BusinessError.ENTITY_NOTFOUND.getDescription())
                        .error(exception.getMessage())
                        .build());
    }
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handler(LockedException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionResponse.builder()
                        .errorCode(BusinessError.ACCOUNT_LOCKED.getCode())
                        .errorDescription(BusinessError.ACCOUNT_LOCKED.getDescription())
                        .error(exception.getMessage())
                        .build());
    }
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handler(DisabledException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionResponse.builder()
                        .errorCode(BusinessError.ACCOUNT_DISABLED.getCode())
                        .errorDescription(BusinessError.ACCOUNT_DISABLED.getDescription())
                        .error(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handler(MethodArgumentNotValidException exception){
        Set<String> map = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach(
                error ->{
                    var message = error.getDefaultMessage();
                    map.add(message);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .validationErrors(map)
                        .build()
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handler(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exception){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .build());

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception){
        return ResponseEntity.status(UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .errorCode(BusinessError.BAD_CREDENTIALS.getCode())
                        .error(exception.getMessage())
                        .errorDescription(BusinessError.BAD_CREDENTIALS.getDescription())
                        .build());

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleException(IllegalArgumentException exception){
        return ResponseEntity.status(UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .errorCode(BusinessError.BAD_CREDENTIALS.getCode())
                        .error(exception.getMessage())
                        .errorDescription(BusinessError.BAD_CREDENTIALS.getDescription())
                        .build());

    }
}





