package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.BadCredentialsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataNotFoundException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.PermissionDeniedCreateUserException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.example.usermicroservice.adapters.driving.http.dto.response.AuthResponse;
import com.example.usermicroservice.adapters.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class UserDetailServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IRolRepository rolRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserDetailServiceImpl userDetailService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetailService = new UserDetailServiceImpl(passwordEncoder, rolRepository, jwtUtils, userRepository);
    }

    @Test
    void testLoadUserByUsername() {
       UserEntity userEntity = new UserEntity();
       userEntity.setEmail("test@example.com");
       userEntity.setPassword("password");

       RolEntity rolEntity = new RolEntity();
       rolEntity.setName("ROLE_USER");
       userEntity.setRol(rolEntity);

       when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

       UserDetails userDetails = userDetailService.loadUserByUsername("test@example.com");

       assertEquals("test@example.com", userDetails.getUsername());
       assertEquals("password", userDetails.getPassword());
    }

    @Test
     void testLoadUserByUsernameNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername("test@example.com"));
    }


    @Test
    void testLoginUser() {
       AuthLoginRequest authLoginRequest = new AuthLoginRequest("test@example.com", "password");

       UserEntity userEntity = new UserEntity();
       userEntity.setEmail("test@example.com");
       userEntity.setPassword("password");

       RolEntity rolEntity = new RolEntity();
       rolEntity.setName("ROLE_USER");
       userEntity.setRol(rolEntity);

       when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));
       when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
       when(jwtUtils.createToken(any(),anyLong())).thenReturn("token");

       AuthResponse authResponse = userDetailService.loginUser(authLoginRequest);

       assertEquals("test@example.com", authResponse.getUsername());
    }


    @Test
    void testValidateUser() {

       Long rolId = 1L;
       String userEmail = "test@example.com";
       String userRole = "ADMIN";

       UserEntity userEntity = new UserEntity();
       userEntity.setEmail(userEmail);
       userEntity.setPassword("password");

       RolEntity userRolEntity = new RolEntity();
       userRolEntity.setName(userRole);
       userEntity.setRol(userRolEntity);

       RolEntity rolEntity = new RolEntity();
       rolEntity.setId(rolId);
       rolEntity.setName("USER");

       when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(userEntity));
       when(rolRepository.findById(rolId)).thenReturn(Optional.of(rolEntity));


       Authentication auth = new UsernamePasswordAuthenticationToken(userEmail, userEntity.getPassword());
       SecurityContextHolder.getContext().setAuthentication(auth);


       assertDoesNotThrow(() -> userDetailService.validateUser(rolId));


       userRole = "USER";
       userRolEntity.setName(userRole);
       userEntity.setRol(userRolEntity);
       when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(userEntity));


       assertThrows(PermissionDeniedCreateUserException.class, () -> userDetailService.validateUser(rolId));
    }

    @Test
     void testValidateUserNotFound() {
        when(rolRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> userDetailService.validateUser(1L));
    }
}