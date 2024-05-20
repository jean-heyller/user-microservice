package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.usermicroservice.domain.api.IUserServicePort;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRestControllerAdapterTest {
    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;

    @Mock
    private UserDetailServiceImpl userDetailService;

    private Long id = 1L;

    private String name = "John";

    private String lastName = "Doe";

    private String email = "prueba@hotmail.com";

    private String password = "password123";

    private String identification = "1234567890";

    private String phone = "+57 304 291 8990";

    private LocalDate birthDate = LocalDate.of(1990, 12, 12);

    Rol rol = new Rol(1L, "ADMIN", "Administrator role");

    private Long idRol = 1L;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {

        Long rolId = 1L;
        AddUserRequest request = new AddUserRequest("", lastName, identification, email, password, phone, birthDate, idRol);
        User user = new User(id, name, lastName, email, password, identification, rol, phone, birthDate);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("john.doe@example.com", "password123", authorities);


        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        doNothing().when(userDetailService).validateUser(rolId);
        when(userRequestMapper.addRequestToUser(request)).thenReturn(user);


        ResponseEntity<Void> response = userRestControllerAdapter.addUser(request);


        verify(userDetailService).validateUser(rolId);
        verify(userServicePort).saveUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}