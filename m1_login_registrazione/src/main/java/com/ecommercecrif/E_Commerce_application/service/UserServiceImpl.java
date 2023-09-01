package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Override
    public UserEntity addUser(UserEntity entity) {

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return repository.save(entity);
    }

    @Transactional
    @Override
    public UserEntity updateUser(String email, UserEntity newEntity) {

        UserEntity userToUpdate = findByEmail(email);
        newEntity.setId(userToUpdate.getId());

        return repository.save(newEntity);

    }

    @Override
    public UserEntity findByEmail(String email) {
        Optional<UserEntity> foundUser = repository.findByEmail(email);
        if(foundUser.isEmpty()){
            throw new EntityNotFoundException();
        }
        return foundUser.get();
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

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
