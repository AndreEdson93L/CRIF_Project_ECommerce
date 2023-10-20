package com.ecommercecrif.E_Commerce_application.exception;

import jakarta.persistence.EntityExistsException;

public class EmailAlreadyInUseException extends EntityExistsException {
    public EmailAlreadyInUseException(String email){
        super("This email: "+email+ " is already in use.");
    }

}
