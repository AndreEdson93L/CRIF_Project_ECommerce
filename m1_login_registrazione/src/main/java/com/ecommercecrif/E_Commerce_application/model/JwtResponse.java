package com.ecommercecrif.E_Commerce_application.model;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
