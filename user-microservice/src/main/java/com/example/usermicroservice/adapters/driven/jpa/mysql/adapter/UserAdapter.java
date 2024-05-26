package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataNotFoundException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.ValueAlreadyExitsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.domain.model.User;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    public static final String USER_EXISTS_ERROR_MESSAGE = "The user";

    public static final String ROLE_EXISTS_ERROR_MESSAGE = "The role";

    private final IUserRepository userRepository;

    private final IRolRepository roleRepository;

    private final IRolEntityMapper rolEntityMapper;

    private final IUserEntityMapper userEntityMapper;


    private final BCryptPasswordEncoder passwordEncoder;




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

        user.setRole(rolEntityMapper.toModel(rolEntity));
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(userEntityMapper.toEntity(user));

    }

    @Override
    public String getRolName(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User"));
        if (userEntity == null) {
            throw new DataNotFoundException(USER_EXISTS_ERROR_MESSAGE);
        }
        String name = userEntity.getRol().getName();
        return name;
    }

    @Override
    public String getPhoneNumber(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User"));
        if (userEntity == null) {
            throw new DataNotFoundException(USER_EXISTS_ERROR_MESSAGE);
        }
        String phoneNumber = userEntity.getPhone();
        return phoneNumber;
    }

    @Override
    public String getEmail(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User"));
        if (userEntity == null) {
            throw new DataNotFoundException(USER_EXISTS_ERROR_MESSAGE);
        }
        String email = userEntity.getEmail();
        return email;
    }

}
