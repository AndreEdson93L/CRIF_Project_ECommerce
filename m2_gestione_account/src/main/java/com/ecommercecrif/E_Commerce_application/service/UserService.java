package com.ecommercecrif.E_Commerce_application.service;


import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;

import java.net.ConnectException;
import java.util.Collection;

public interface UserService {
    UserResponseDTO addUser(RegisterUserDTO entity) throws ConnectException;

    UserResponseDTO updateUser(String email, UpdateUserDTO updateUserDTO);

    UserEntity findByEmail(String email);

    UserResponseDTO getUserByEmailInJwt(String token);
    Boolean deleteByEmailInJwt(String token);

    UserResponseDTO updateUserByEmailInJwt(String token, UpdateUserDTO updateUserDTO);

    Collection<UserEntity> findAll();

    boolean deleteByEmail(String email);

}
