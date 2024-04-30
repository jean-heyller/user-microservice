package com.example.usermicroservice.domain.api.usecase;

import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.model.User;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;


 class UserUseCaseTest {


    private IUserPersistencePort userPersistencePort;


    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userPersistencePort = org.mockito.Mockito.mock(IUserPersistencePort.class);
        userUseCase = new UserUseCase(userPersistencePort);
    }



    @Test
     void saveUser() {
        Rol rol = new Rol(1L, "ADMIN", "administrator");
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", "1234567890", rol);
        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(user);
    }

}