package com.ecommercecrif.E_Commerce_application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NICKNAME", unique = true, nullable = false)
    private String nickname;

    @Column(name = "BALANCE")
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private EnumRole role;

    public UserEntity(String email, String nickname, String password, double balance, EnumRole role) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }
}
