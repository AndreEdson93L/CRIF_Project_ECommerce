package com.ecommercecrif.E_Commerce_application.exception;

import jakarta.ws.rs.BadRequestException;

public class NicknameAlreadyInUseException extends IllegalStateException {
    public NicknameAlreadyInUseException(String nickname){
        super("This nickname: "+nickname+ " is already in use.");
    }
}