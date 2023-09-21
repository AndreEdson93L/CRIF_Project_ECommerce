package com.ecommercecrif.E_Commerce_application.repository;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    long deleteByEmail(String email);

    Collection<UserEntity> findAllByOrderByEmailAsc();


}
