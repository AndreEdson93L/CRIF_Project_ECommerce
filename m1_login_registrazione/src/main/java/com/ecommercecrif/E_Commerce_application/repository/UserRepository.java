package com.ecommercecrif.E_Commerce_application.repository;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
