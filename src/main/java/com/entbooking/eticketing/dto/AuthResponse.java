package com.entbooking.eticketing.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private long expiresAt;
}