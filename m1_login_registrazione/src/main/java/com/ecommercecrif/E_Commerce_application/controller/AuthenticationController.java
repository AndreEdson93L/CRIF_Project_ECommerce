package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {


    @PostMapping("/register")
    public UserEntity register(UserEntity user){
        //toDo
        return null;
    }


    //endpoint autenticazione
    @PostMapping("/authentication")
    public UserEntity authenticate(@RequestHeader("Authorization") String basicAuth){
        //toDo
        return null;
    }

    @GetMapping("/getUser/{email}")
    public UserEntity getUser(UserEntity user){
        //toDo
        return null;
    }

    @DeleteMapping("/deleteUser/{email}")
    public UserEntity deleteUser(UserEntity user){
        //toDo
        return null;
    }

    @PutMapping("/updateUser/{email}")
    public UserEntity updateUser(UserEntity user){
        //toDo
        return null;
    }

    @GetMapping("/findAllUsers")
    public List<UserEntity> getAllUsers(){
        //toDo
        return null;
    }



}
