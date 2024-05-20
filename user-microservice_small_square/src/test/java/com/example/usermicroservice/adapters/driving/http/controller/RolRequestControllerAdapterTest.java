package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddRolRequest;
import com.example.usermicroservice.adapters.driving.http.mapper.IRolRequestMapper;
import com.example.usermicroservice.domain.api.IRolServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RolRequestControllerAdapterTest {

    @InjectMocks
    RolRequestControllerAdapter rolRequestControllerAdapter;

    @Mock
    IRolRequestMapper rolRequestMapper;

    @Mock
    IRolServicePort rolServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRol() {
        // Arrange
        RolRequestControllerAdapter rolRequestControllerAdapter = new RolRequestControllerAdapter(rolRequestMapper, rolServicePort);
        AddRolRequest addRolRequest = new AddRolRequest("Test Role", "Test Description");
        when(rolRequestMapper.addRequestToRol(addRolRequest)).thenReturn(any());

        ResponseEntity<Void> result = rolRequestControllerAdapter.addRol(addRolRequest);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), result);
        verify(rolServicePort, times(1)).saveRol(any());
    }
}