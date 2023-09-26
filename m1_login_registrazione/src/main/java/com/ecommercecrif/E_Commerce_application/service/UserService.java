package com.ecommercecrif.E_Commerce_application.service;


import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;

import java.util.Collection;

public interface UserService {
    UserResponseDTO addUser(RegisterUserDTO entity);

    UserResponseDTO updateUser(String email, UpdateUserDTO updateUserDTO);
    UserResponseDTO updateUserToAdmin(String email);

    UserEntity findByEmail(String email);

    Collection<UserEntity> findAll();

    boolean deleteByEmail(String email);

}
