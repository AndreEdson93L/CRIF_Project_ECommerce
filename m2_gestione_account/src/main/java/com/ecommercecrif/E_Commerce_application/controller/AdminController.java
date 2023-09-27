package com.ecommercecrif.E_Commerce_application.controller;

import com.ecommercecrif.E_Commerce_application.mapper.UserMapper;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.service.AdminService;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/admin-management")
public class AdminController {


    private UserServiceImpl userService;
    private AdminService adminService;
    private UserMapper userMapper;

    @Autowired
    public AdminController( UserServiceImpl userService, AdminService adminService, UserMapper userMapper){

        this.adminService = adminService;
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @Operation(summary = "get-all-users")
    @GetMapping("admin/find-all-users")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public Collection<UserEntity> getAllUsers(){
        return userService.findAll();
    }

    @Operation(summary = "get-User-Details-By-Email")
    @GetMapping("admin/get-user-details/{email}")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public UserResponseDTO getUserDetails(@PathVariable String email){
        return userMapper.userEntityToDto(userService.findByEmail(email));
    }

    @Operation(summary =  "update-User-Details")
    @PutMapping("admin/update-user-details/{email}")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public UserResponseDTO updateUserDetails(@PathVariable String email, @Valid @RequestBody UpdateUserDTO updateUserDTO, @RequestHeader(name = "Authorization") String token){
        return adminService.updateUser(email, token, updateUserDTO);
    }

    @Operation(summary = "promote-User-To-Admin")
    @PutMapping("admin/promote-user/{email}")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public UserResponseDTO promoteUserToAdmin(@PathVariable String email, @RequestHeader(name = "Authorization") String token){
        return adminService.promoteToAdmin(email, token);
    }

    @Operation(summary = "delete-User")
    @DeleteMapping("admin/delete-user/{email}")
    @PreAuthorize("hasAuthority('SCOPE_[ADMIN]')")
    public boolean deleteUser(@PathVariable String email, @RequestHeader(name="Authorization") String token) {
        return adminService.deleteByEmail(email, token);
    }
}
