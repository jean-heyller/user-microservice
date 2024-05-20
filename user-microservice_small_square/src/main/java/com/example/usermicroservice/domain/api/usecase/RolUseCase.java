package com.example.usermicroservice.domain.api.usecase;

import com.example.usermicroservice.domain.api.IRolServicePort;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.spi.IRolPersistencePort;

public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;


    public RolUseCase(IRolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }



        @Override
        public void saveRol(Rol rol) {
            rolPersistencePort.saveRol(rol);

        }
}
