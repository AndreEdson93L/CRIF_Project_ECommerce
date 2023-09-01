package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.service.UserService;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    UserServiceImpl userService;
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user){
        return userService.addUser(user);
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
        //toDo
        return userService.deleteByEmail(email);
    }

    @PutMapping("/update-user/{email}")
    public UserEntity updateUser(@PathVariable String email, UserEntity updateUserDto){

        return userService.updateUser(email, updateUserDto);
    }

    @GetMapping("/find-all-users")
    public Collection<UserEntity> getAllUsers(){

        return userService.findAll();
    }



}
