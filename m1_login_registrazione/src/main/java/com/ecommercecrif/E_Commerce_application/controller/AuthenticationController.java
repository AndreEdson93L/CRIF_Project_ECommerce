package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.security.TokenService;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateEmailDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserServiceImpl userService;

    @Operation(summary = "register-new-user")
    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterUserDTO registerUserDTO){

        return userService.addUser(registerUserDTO);
    }
    @Operation(summary = "get-all-users")
    @GetMapping("admin/find-all-users")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public Collection<UserEntity> getAllUsers(){

        return userService.findAll();
    }

  /*  @Operation(summary = "get-User-By-Email")
    @GetMapping("get-user/{email}")
    public UserEntity getUser(@PathVariable String email){
        //toDo
        return userService.findByEmail(email);
    }
*/
    @DeleteMapping("/delete-user/{email}")
    public boolean deleteUser(@PathVariable String email){

        return userService.deleteByEmail(email);
    }

    @PutMapping("/update-user/{email}")
    public UserResponseDTO updateUser(@PathVariable String email,@Valid @RequestBody UpdateEmailDTO updateUserDto){

        return userService.updateUser(email, updateUserDto);
    }

    @Operation(summary = "promote-to-admin")
    @PutMapping("admin/promote-user/{email}")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public UserResponseDTO promoteToAdmin(@PathVariable String email){

        return userService.updateUserToAdmin(email);
    }


    /*
        endpoint che permette una Basic Auth e restituisce
        una stringa rappresentante il Jwt usabile per la Bearer Auth
        negl'altri endpoints
     */
    @Operation(summary = "generate-Jwt-Token")
    @PostMapping("/login")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

}
