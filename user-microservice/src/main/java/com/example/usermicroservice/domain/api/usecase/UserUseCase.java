package com.example.usermicroservice.domain.api.usecase;

import com.example.usermicroservice.domain.api.IUserServicePort;
import com.example.usermicroservice.domain.model.User;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }
}
