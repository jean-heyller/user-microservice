package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.example.usermicroservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.example.usermicroservice.adapters.driving.http.dto.response.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

 class AuthenticactionControllerTest {

    @Mock
    private UserDetailServiceImpl userDetailService;

    @InjectMocks
    private AuthenticactionController authenticactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

     @Test
     void testLogin() {
         AuthLoginRequest authLoginRequest = new AuthLoginRequest("test@example.com", "password");
         AuthResponse authResponse = new AuthResponse("test@example.com", "token", "Bearer", true);

         when(userDetailService.loginUser(authLoginRequest)).thenReturn(authResponse);

         ResponseEntity<AuthResponse> response = authenticactionController.login(authLoginRequest);

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(authResponse, response.getBody());
     }
}