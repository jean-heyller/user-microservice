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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

 class UserAdapterTest {


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
        userAdapter = new UserAdapter(userRepository, roleRepository, rolEntityMapper, userEntityMapper, passwordEncoder);
    }


     private Long id = 1L;

     private String name = "John";

     private String lastName = "Doe";

     private String email = "prueba@hotmail.com";

     private String password = "password123";

     private String identification = "1234567890";

     private String phone = "+57 304 291 8990";

     private LocalDate birthDate = LocalDate.of(1990, 12, 12);

     Rol rol = new Rol(1L, "ADMIN", "Administrator role");

     @Test
     void testSaveUser() {

         User user = new User(id, name, lastName, email, password, identification, rol, phone, birthDate);
         RolEntity rolEntity = new RolEntity(1L, "ADMIN", "Administrator role");

         when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
         when(roleRepository.findById(user.getRol().getId())).thenReturn(Optional.of(rolEntity));
         when(rolEntityMapper.toModel(any(RolEntity.class))).thenReturn(rol);
         when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

         userAdapter.saveUser(user);

         verify(userRepository).save(any());
     }

    @Test
     void testSaveUserThrowsValueAlreadyExistsException() {

        User user = new User(id, name, lastName, email, password, identification, rol, phone, birthDate);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));

        assertThrows(ValueAlreadyExitsException.class, () -> userAdapter.saveUser(user));
    }

    @Test
     void testSaveUserThrowsDataNotFoundException() {

        User user = new User(id, name, lastName, email, password, identification, rol, phone, birthDate);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findById(user.getRol().getId())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> userAdapter.saveUser(user));
    }
}