package com.ecommercecrif.E_Commerce_application.model.dto;

import com.ecommercecrif.E_Commerce_application.model.EnumRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size
    private String password;
}
