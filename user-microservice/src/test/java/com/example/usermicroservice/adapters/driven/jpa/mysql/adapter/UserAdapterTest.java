package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataNotFoundException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.ValueAlreadyExitsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRolRepository roleRepository;

    @Mock
    private IRolEntityMapper rolEntityMapper;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAdapter userAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        Rol rol = new Rol(1L, "ADMIN", "Administrator role");
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", "1234567890", rol);
        RolEntity rolEntity = new RolEntity(1L, "ADMIN", "Administrator role");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findById(user.getRol().getId())).thenReturn(Optional.of(rolEntity));
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

        userAdapter.saveUser(user);

        verify(userRepository).save(any());
    }

    @Test
    public void testSaveUserThrowsValueAlreadyExistsException() {
        Rol rol = new Rol(1L, "ADMIN", "Administrator role");
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", "1234567890", rol);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));

        assertThrows(ValueAlreadyExitsException.class, () -> userAdapter.saveUser(user));
    }

    @Test
    public void testSaveUserThrowsDataNotFoundException() {
        Rol rol = new Rol(1L, "ADMIN", "Administrator role");
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", "1234567890", rol);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findById(user.getRol().getId())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> userAdapter.saveUser(user));
    }
}