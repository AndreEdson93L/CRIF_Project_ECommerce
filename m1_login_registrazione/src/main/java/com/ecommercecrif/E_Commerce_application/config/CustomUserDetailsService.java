package com.ecommercecrif.E_Commerce_application.config;


import com.ecommercecrif.E_Commerce_application.model.Role;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optUser =repository.findByEmail(email);
        if (optUser.isEmpty()){
            throw new EntityNotFoundException("Username not found");
        }
        UserEntity user = optUser.get();
        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role){
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
