package com.entbooking.eticketing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TheatreRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String city;

    // Getters and Setters
}

