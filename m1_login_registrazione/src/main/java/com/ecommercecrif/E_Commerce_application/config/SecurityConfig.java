package com.ecommercecrif.E_Commerce_application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationManager authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity

                .authorizeRequests()

                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/index.html")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/v1/authentication/find-all-users")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/v1/authentication/register/")).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                //nota bene disabilitare crsf e non impostare su Stateless le session è una bad practice per la sicurezza
                //Necessario per visualizzare l'interfaccia web di h2 DB
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .sameOrigin()
                        ))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();
    }
}
