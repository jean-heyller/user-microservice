package com.example.usermicroservice.domain.api.usecase;



import com.example.usermicroservice.domain.api.IRolServicePort;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.spi.IRolPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class RolUseCaseTest {

    private IRolPersistencePort rolPersistencePort;

    private IRolServicePort rolServicePort;


    @BeforeEach
    void setUp() {
        rolPersistencePort = Mockito.mock(IRolPersistencePort.class);
        rolServicePort = new RolUseCase(rolPersistencePort);
    }

    @Test
    void saveRol() {
        Rol rol = new Rol(1L,"ADMIN","administrator");

        rolServicePort.saveRol(rol);

        verify(rolPersistencePort, Mockito.times(1)).saveRol(rol);
    }

}