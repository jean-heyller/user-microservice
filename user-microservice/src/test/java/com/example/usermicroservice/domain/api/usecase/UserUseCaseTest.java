package com.example.usermicroservice.domain.api.usecase;

import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.model.User;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;


 class UserUseCaseTest {


    private IUserPersistencePort userPersistencePort;


    private UserUseCase userUseCase;

    private Long id = 1L;

    private String name = "John";

    private String lastName = "Doe";

    private String email = "prueba@hotmail.com";

    private String password = "password123";

    private String identification = "1234567890";

    private String phone = "+57 304 291 8990";



    private Rol rol = new Rol(1L, "ADMIN", "administrator");


    @BeforeEach
    void setUp() {
        userPersistencePort = org.mockito.Mockito.mock(IUserPersistencePort.class);
        userUseCase = new UserUseCase(userPersistencePort);
    }



    @Test
     void saveUser() {
           LocalDate birthDate = LocalDate.of(1990, 12, 12);
        User user = new User(id,  name, lastName, email, password, identification,
                rol, phone, birthDate);
        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(user);
    }

}