package com.ecommercecrif.E_Commerce_application.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {


    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email shouldn't be more then 255 characters")
    private String email;

    //@Size(min = 4, max = 255, message = "Nickname should be between 4 and 255 characters")
    private String nickname;


}
