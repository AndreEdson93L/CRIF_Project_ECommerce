/*
package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.model.EnumRole;
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
        // Kenny is poor, so he has a low balance
        repository.save(new UserEntity("kenny@southpark.com", "Kenny_McCormick", passwordEncoder.encode("kennyPass"), 10.0, EnumRole.valueOf("USER")));

        // Cartman
        repository.save(new UserEntity("cartman@southpark.com", "Eric_Cartman", passwordEncoder.encode("cartmanPass"), 200.0, EnumRole.valueOf("USER")));

        // Stan
        repository.save(new UserEntity("stan@southpark.com", "Stan_Marsh", passwordEncoder.encode("stanPass"), 100.0, EnumRole.valueOf("USER")));

        // Kyle
        repository.save(new UserEntity("kyle@southpark.com", "Kyle_Broflovski", passwordEncoder.encode("kylePass"), 100.0, EnumRole.valueOf("USER")));

        // Chef
        repository.save(new UserEntity("chef@southpark.com", "Chef", passwordEncoder.encode("chefPass"), 500.0, EnumRole.valueOf("ADMIN")));
    }
}

*/
