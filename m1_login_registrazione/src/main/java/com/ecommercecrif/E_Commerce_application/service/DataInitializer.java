/*
package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.model.Role;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {

    @Autowired
    private UserRepository repository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void initData(){

        repository.save(new UserEntity("email1@yahoo.com", passwordEncoder.encode("pass1"), Role.valueOf("ADMIN")));
        repository.save(new UserEntity("email2@yahoo.com", passwordEncoder.encode("pass2"), Role.valueOf("USER")));
        repository.save(new UserEntity("email3@yahoo.com", passwordEncoder.encode("pass3"), Role.valueOf("USER")));
    }
}
*/
