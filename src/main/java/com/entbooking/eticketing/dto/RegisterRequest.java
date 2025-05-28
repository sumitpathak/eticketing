package com.entbooking.eticketing.dto;

import com.entbooking.eticketing.config.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Role role;
    // Getters and Setters
}
