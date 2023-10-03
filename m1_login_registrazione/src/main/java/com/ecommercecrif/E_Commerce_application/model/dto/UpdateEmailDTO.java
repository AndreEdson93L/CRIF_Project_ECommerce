package com.ecommercecrif.E_Commerce_application.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmailDTO {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

}
