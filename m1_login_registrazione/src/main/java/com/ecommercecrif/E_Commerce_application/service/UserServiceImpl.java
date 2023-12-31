package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.exception.UserNotFoundException;
import com.ecommercecrif.E_Commerce_application.mapper.UserMapper;
import com.ecommercecrif.E_Commerce_application.model.EnumRole;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateEmailDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Transactional
    @Override
    public UserResponseDTO addUser(RegisterUserDTO registerUserDTO) {


        UserEntity userEntity = userMapper.dtoToUserEntity(registerUserDTO);

        //This is a workaround to remove the role = null problem in insertion into DB
        userEntity.setRole(EnumRole.valueOf("USER"));

        UserEntity savedUser = repository.save(userEntity);

        return userMapper.userEntityToDto(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(String email, UpdateEmailDTO updateUserDTO) {

        //verifying if user exists
        UserEntity userToUpdate = findByEmail(email);

        //setting email
        userToUpdate.setEmail(updateUserDTO.getEmail());

        //saving user in the repo
        repository.save(userToUpdate);

        //returning a dto (email, role)
        return userMapper.userEntityToDto(userToUpdate);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUserToAdmin(String email) {

        UserEntity userToUpdate = findByEmail(email);
        if(userToUpdate.getRole().equals(EnumRole.ADMIN)){
            return userMapper.userEntityToDto(userToUpdate);
        }
        userToUpdate.setRole(EnumRole.ADMIN);
        repository.save(userToUpdate);
        //returning a dto (email, role)
        return userMapper.userEntityToDto(userToUpdate);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException(email)
        );
    }


    @Override
    public Collection<UserEntity> findAll() {
        return repository.findAllByOrderByEmailAsc();
    }

    @Transactional
    @Override
    public boolean deleteByEmail(String email) {
        long deletedRecords = repository.deleteByEmail(email);
        return deletedRecords == 1;
    }


}
