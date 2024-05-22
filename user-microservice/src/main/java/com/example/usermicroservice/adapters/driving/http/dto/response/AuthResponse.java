package com.example.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {
    private final String username;

    private final String message;

    private final String jwt;

    private final boolean status;

    public String getToken() {
        return jwt;
    }
}
