package com.ecommercecrif.E_Commerce_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    // Helper method to create HttpMessageError
    private ResponseEntity<HttpMessageError> buildHttpMessageError(String message, HttpStatus status) {
        HttpMessageError httpMessageError = new HttpMessageError(message, LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, status);
    }


    @ExceptionHandler({
            EmailAlreadyInUseException.class,
            NicknameAlreadyInUseException.class
    })
    public ResponseEntity<HttpMessageError> handleCustomAlreadyInUseExceptions(Exception ex) {
        return buildHttpMessageError(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpMessageError> handleUserNotFoundException(UserNotFoundException ex) {
        HttpMessageError httpMessageError  = new HttpMessageError(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( AccessDeniedException.class)
    public ResponseEntity<HttpMessageError> handleEmailAlreadyInUseException( AccessDeniedException ex) {
        HttpMessageError httpMessageError  = new HttpMessageError(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, HttpStatus.UNAUTHORIZED);
    }


}
