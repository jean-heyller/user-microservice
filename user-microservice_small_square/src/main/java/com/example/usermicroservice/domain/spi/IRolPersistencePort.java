package com.example.usermicroservice.domain.spi;

import com.example.usermicroservice.domain.model.Rol;

public interface IRolPersistencePort {

    void saveRol(Rol rol);
}
