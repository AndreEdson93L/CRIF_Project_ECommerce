package com.ecommercecrif.E_Commerce_application.model.dto;

import com.ecommercecrif.E_Commerce_application.model.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String email;
    private String nickname;
    private EnumRole role;
    private double balance;
}
