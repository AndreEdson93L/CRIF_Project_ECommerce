package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.*;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.net.ConnectException;


@RestController
@RequestMapping("/api/v1/user-management")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping("test")
    public UserEntity test() throws Exception {
        throw new Exception("this is a generic exception");
    }




    @Operation(summary = "account-registration")
    @PostMapping("register-account")
    public UserResponseDTO registerAccount(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws ConnectException {
        return userService.addUser(registerUserDTO);
    }


    @Operation(summary = "get-User-By-Email-contained-in-jwt")
    @GetMapping("get-user-details")
    public UserResponseDTO getUser(@RequestHeader (name="Authorization") String token){
            return userService.getUserByEmailInJwt(token);
    }

    @Operation(summary = "delete-User-By-Email-contained-in-jwt")
    @DeleteMapping("/delete-user-details")
    public boolean deleteUser(@RequestHeader (name="Authorization") String token){
        return userService.deleteByEmailInJwt(token);
    }

    @Operation(summary = "update-User-By-Email-contained-in-jwt")
    @PutMapping("/update-user-details")
    public UserResponseDTO updateUser(@RequestHeader (name="Authorization") String token, @Valid @RequestBody UpdateUserDTO updateUserDto){
        return userService.updateUserByEmailInJwt(token, updateUserDto);
    }
}
