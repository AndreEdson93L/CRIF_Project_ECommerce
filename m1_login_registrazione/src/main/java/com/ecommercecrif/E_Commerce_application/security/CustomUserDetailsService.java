package com.ecommercecrif.E_Commerce_application.security;


import com.ecommercecrif.E_Commerce_application.exception.UserNotFoundException;
import com.ecommercecrif.E_Commerce_application.model.EnumRole;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import com.ecommercecrif.E_Commerce_application.service.UserServiceImpl;
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
    public UserServiceImpl userService;

    public CustomUserDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user =userService.findByEmail(email);

        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(EnumRole role){
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
