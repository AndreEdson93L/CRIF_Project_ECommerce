package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.mapper.UserMapper;
import com.ecommercecrif.E_Commerce_application.model.EnumRole;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminServiceImpl implements AdminService {

    private UserRepository repository;
    private UserMapper userMapper;
    private RestTemplate restTemplate;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminServiceImpl(UserRepository repository, UserMapper userMapper, RestTemplate restTemplate) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public UserResponseDTO promoteToAdmin(String email, String token) {

        String url = "http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/admin/promote-user/" +email;

        UserEntity userToUpdate = repository.findByEmail(email).orElseThrow(NotFoundException::new);
        userToUpdate.setRole(EnumRole.ADMIN);

        HttpEntity<String> entity = createHttpEntityWithJwtHeader(token);

        restTemplate.exchange(url, HttpMethod.PUT, entity, UserResponseDTO.class);

        repository.save(userToUpdate);

        return userMapper.userEntityToDto(userToUpdate);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public boolean deleteByEmail(String email, String token) {
        String url = "http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/delete-user/" + email;

        HttpEntity<String> entity = createHttpEntityWithJwtHeader(token);

        restTemplate.exchange(url, HttpMethod.DELETE, entity, Boolean.class);

        long deletedRecords = repository.deleteByEmail(email);

        return deletedRecords == 1;
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(String email, String token, UpdateUserDTO updateUserDTO) {

        String url = "http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/update-user/" +email;

        UserEntity userToUpdate = repository.findByEmail(email).orElseThrow(NotFoundException::new);
        userToUpdate.setEmail(updateUserDTO.getEmail());
        userToUpdate.setNickname(updateUserDTO.getNickname());

        HttpEntity<UpdateUserDTO> entity = createHttpEntityWithJWTHeaderAndDTO(token, updateUserDTO);

        restTemplate.exchange(url, HttpMethod.PUT, entity, UserResponseDTO.class);
        repository.save(userToUpdate);

        return userMapper.userEntityToDto(userToUpdate);
    }

    // Utility function to create HttpEntity with JWT token
    public HttpEntity<String> createHttpEntityWithJwtHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return entity;
    }

    // Utility function to create HttpEntity with JWT token && DTO
    public HttpEntity<UpdateUserDTO> createHttpEntityWithJWTHeaderAndDTO (String token, UpdateUserDTO updateUserDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<UpdateUserDTO> entity = new HttpEntity<UpdateUserDTO>(updateUserDTO, headers);
        return entity;
    }
}
