package com.example.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AuthLoginRequest {
    @NotBlank(message = "Username is required")
    private final String username;

    @NotBlank(message = "Password is required")
    private final String password;
}
