package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataNotFoundException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.ValueAlreadyExitsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.domain.model.User;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    public static final String USER_EXISTS_ERROR_MESSAGE = "The user";

    public static final String ROLE_EXISTS_ERROR_MESSAGE = "The role";

    private final IUserRepository userRepository;

    private final IRolRepository roleRepository;

    private final IRolEntityMapper rolEntityMapper;

    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        String normalizedNames = user.getName().toLowerCase();
        user.setName(normalizedNames);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ValueAlreadyExitsException(USER_EXISTS_ERROR_MESSAGE);
        }

        Optional<RolEntity> rolEntityOptional = roleRepository.findById(user.getRol().getId());
        if (rolEntityOptional.isEmpty()) {
            throw new DataNotFoundException(ROLE_EXISTS_ERROR_MESSAGE);
        }
        RolEntity rolEntity = rolEntityOptional.get();
        user.SetRol(rolEntityMapper.toModel(rolEntity));
        userRepository.save(userEntityMapper.toEntity(user));

    }


}
