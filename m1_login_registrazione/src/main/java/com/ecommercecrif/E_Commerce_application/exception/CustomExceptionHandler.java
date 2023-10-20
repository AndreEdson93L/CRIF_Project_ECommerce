package com.ecommercecrif.E_Commerce_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {


    private ResponseEntity<HttpMessageError> buildHttpMessageError(String message, HttpStatus status) {
        HttpMessageError httpMessageError = new HttpMessageError(message, LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, status);
    }





    // Custom exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpMessageError> handleUserNotFoundException(UserNotFoundException ex) {
        return buildHttpMessageError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpMessageError> handleAccessDeniedException(AccessDeniedException ex) {
        HttpMessageError httpMessageError  = new HttpMessageError(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpMessageError> handleBadCredentialsException(BadCredentialsException ex) {
        HttpMessageError httpMessageError  = new HttpMessageError(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<HttpMessageError> handleBadCredentialsException(EmailAlreadyInUseException ex) {
        HttpMessageError httpMessageError  = new HttpMessageError(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpMessageError> handleAuthenticationException(AuthenticationException ex) {
        System.out.println(ex.getMessage());
        HttpMessageError httpMessageError  = new HttpMessageError(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(httpMessageError, HttpStatus.UNAUTHORIZED);
    }


}
/*
  // Handle validation errors thrown by @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError("Validation failed", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
 // Handle other exceptions (not handle by @Valid)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
        // Logging
        System.out.println("Handling general exception: " + ex.getClass().getName());

        ApiError apiError =
                new ApiError(ex.getMessage(), List.of("Error occurred"));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 */