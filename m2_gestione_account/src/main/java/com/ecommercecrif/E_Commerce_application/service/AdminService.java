package com.ecommercecrif.E_Commerce_application.service;


import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import org.springframework.http.HttpEntity;

public interface AdminService {
    UserResponseDTO promoteToAdmin(String email, String token);
    UserEntity findByEmail(String email);
    boolean deleteByEmail(String email, String token);
    HttpEntity<String> createHttpEntityWithJwtHeader(String token);
    UserResponseDTO updateUser(String email, String token, UpdateUserDTO updateUserDTO);
}
