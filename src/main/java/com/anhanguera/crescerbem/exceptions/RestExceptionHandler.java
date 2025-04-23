package com.anhanguera.crescerbem.exceptions;

import com.anhanguera.crescerbem.payloads.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDetails> handlerBusinessException(BusinessException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Business exception")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("EntityNotFound exception")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDetails> handlerBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("BadCredentials exception")
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(TokenJwtInvalidException.class)
    public ResponseEntity<ExceptionDetails> handlerTokenJwtInvalidException(TokenJwtInvalidException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("TokenJwtInvalid exception")
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("UsernameNotFound exception")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerBusinessException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Method Valid exception")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(ex.getBindingResult().getFieldError().getDefaultMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
