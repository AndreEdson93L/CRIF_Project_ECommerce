package com.ecommercecrif.E_Commerce_application.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpMessageError {
    private String message;
    private LocalDateTime localDateTime;
}
