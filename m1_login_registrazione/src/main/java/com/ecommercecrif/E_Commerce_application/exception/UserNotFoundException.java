package com.ecommercecrif.E_Commerce_application.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String email){
        super("User with " + email + " hasn't been found.");
    }
}
