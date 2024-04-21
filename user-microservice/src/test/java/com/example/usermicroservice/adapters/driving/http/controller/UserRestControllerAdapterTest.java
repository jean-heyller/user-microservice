package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.usermicroservice.domain.api.IUserServicePort;
import com.example.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRestControllerAdapterTest {
    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        AddUserRequest request = new AddUserRequest("John", "Doe", "1234567890", "john.doe@example.com", "password123", 1L);
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", "1234567890", null);
        when(userRequestMapper.addRequestToUser(request)).thenReturn(user);

        ResponseEntity<Void> response = userRestControllerAdapter.addUser(request);

        verify(userServicePort).saveUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}