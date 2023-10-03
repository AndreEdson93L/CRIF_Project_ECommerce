package com.ecommercecrif.E_Commerce_application.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService service;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails user = service.loadUserByUsername(authentication.getName());

        if(passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            final String password = authentication.getCredentials().toString();
            Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();
            authorities.add(new SimpleGrantedAuthority(user.getAuthorities().toString()));


            return new UsernamePasswordAuthenticationToken(user.getUsername(),password, authorities);
        } else{
            throw new BadCredentialsException("Provided password is incorrect");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
