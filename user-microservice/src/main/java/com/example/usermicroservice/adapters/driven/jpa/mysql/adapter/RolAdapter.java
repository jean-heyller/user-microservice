package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.ValueAlreadyExitsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.spi.IRolPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolAdapter implements IRolPersistencePort {

    private static final String ROL_EXISTS_ERROR_MESSAGE = "the Rol";

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    @Override
public void saveRol(Rol rol) {
        String normalizedRolName = rol.getName().toLowerCase();
        if(rolRepository.findByName(normalizedRolName).isPresent()){
            throw new ValueAlreadyExitsException(ROL_EXISTS_ERROR_MESSAGE);
        }

        rol.setName(normalizedRolName);

        rolRepository.save(rolEntityMapper.toEntity(rol));

    }

}
