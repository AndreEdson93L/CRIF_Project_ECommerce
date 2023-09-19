package com.ecommercecrif.E_Commerce_application.controller;


//import com.ecommercecrif.E_Commerce_application.config.TokenService;
//import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
//import org.springframework.security.core.Authentication;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collection;




@RestController
@RequestMapping("/api/v1/user-management")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    //@Autowired
    //private TokenService tokenService;

    @Autowired
    UserServiceImpl userService;

    @Operation(summary = "get-all-users")
    @GetMapping("admin/find-all-users")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public Collection<UserEntity> getAllUsers(){
        return userService.findAll();
    }
    @Operation(summary = "get-User-By-Email")
    @GetMapping("get-user/{email}")
    public UserEntity getUser(@PathVariable String email){
        return userService.findByEmail(email);
    }
    @DeleteMapping("/delete-user/{email}")
    public boolean deleteUser(@PathVariable String email){
        return userService.deleteByEmail(email);
    }
    @PutMapping("/update-user/{email}")
    public UserResponseDTO updateUser(@PathVariable String email,@Valid @RequestBody UpdateUserDTO updateUserDto){
        return userService.updateUser(email, updateUserDto);
    }
}
