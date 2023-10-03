package com.ecommercecrif.E_Commerce_application.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BadRequestException;

public class EmailAlreadyInUseException extends IllegalStateException {
    public EmailAlreadyInUseException(String email){
        super("This email: "+email+ " is already in use.");
    }
}
