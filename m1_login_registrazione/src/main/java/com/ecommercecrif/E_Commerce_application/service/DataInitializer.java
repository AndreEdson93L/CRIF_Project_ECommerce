
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
        repository.save(new UserEntity("kenny@southpark.com", passwordEncoder.encode("kennyPass"),  EnumRole.valueOf("USER")));

        // Cartman
        repository.save(new UserEntity( "cartman@southpark.com", passwordEncoder.encode("cartmanPass"), EnumRole.valueOf("USER")));

        // Stan
        repository.save(new UserEntity("stan@southpark.com", passwordEncoder.encode("stanPass"), EnumRole.valueOf("USER")));

        // Kyle
        repository.save(new UserEntity("kyle@southpark.com", passwordEncoder.encode("kylePass"), EnumRole.valueOf("USER")));

        // Chef
        repository.save(new UserEntity("chef@southpark.com", passwordEncoder.encode("chefPass"), EnumRole.valueOf("ADMIN")));
    }
}

