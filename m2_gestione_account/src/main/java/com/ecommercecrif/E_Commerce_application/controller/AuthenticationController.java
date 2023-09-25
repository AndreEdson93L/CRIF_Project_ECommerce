package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.exception.EmailAlreadyInUseException;
import com.ecommercecrif.E_Commerce_application.exception.NicknameAlreadyInUseException;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.AuthUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/user-management")
public class AuthenticationController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    UserServiceImpl userService;

    @Operation(summary = "account-registration")
    @PostMapping("register-account")
    public UserResponseDTO registerAccount(@Valid @RequestBody RegisterUserDTO registerUserDTO){

        if(userService.existByEmail(registerUserDTO.getEmail())){
            throw new EmailAlreadyInUseException(registerUserDTO.getEmail());
        }
        if(userService.existByNickname(registerUserDTO.getNickname())){
            throw new NicknameAlreadyInUseException(registerUserDTO.getNickname());
        }

        AuthUserDTO authUserDTO = new AuthUserDTO(registerUserDTO.getEmail(), registerUserDTO.getPassword());

        //restTemplate.postForEntity("http://localhost:8090/api/v1/authentication/register", authUserDTO, AuthUserDTO.class );

        webClientBuilder.build()
                .post()
                .uri("http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/register")
                .body(Mono.just(authUserDTO), AuthUserDTO.class)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();


        return userService.addUser(registerUserDTO);
    }

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
