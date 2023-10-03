package com.ecommercecrif.E_Commerce_application.model.dto;

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

    @Email(message = "Given email is not valid, try something like 'email@email.com'")
    @NotBlank(message = "Email is required")
    @Size(max = 255, message = "Email shouldn't be more then 255 characters")
    private String email;

    @NotBlank(message = "Nickname is required")
    @Size(min = 4, max = 255, message = "Nickname should be between 4 and 255 characters")
    private String nickname;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 25, message = "Password should be between 8 and 25 characters")
    private String password;

}
