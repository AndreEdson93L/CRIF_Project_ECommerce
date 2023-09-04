package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.exception.UserNotFoundException;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody RegisterUserDTO registerUserDTO){
        return userService.addUser(registerUserDTO);
    }
    @GetMapping("/find-all-users")
    public Collection<UserEntity> getAllUsers(){

        return userService.findAll();
    }

    //endpoint autenticazione
    @PostMapping("/authentication")
    public UserEntity authenticate(@RequestHeader("Authorization") String basicAuth){
        //toDo
        return null;
    }

    @GetMapping("get-user/{email}")
    @Operation(summary = "get-User-By-Email")
    public UserEntity getUser(@PathVariable String email){
        //toDo
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
