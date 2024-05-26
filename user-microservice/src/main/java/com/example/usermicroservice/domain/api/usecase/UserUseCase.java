package com.example.usermicroservice.domain.api.usecase;

import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.UnderageException;
import com.example.usermicroservice.domain.api.IUserServicePort;
import com.example.usermicroservice.domain.model.User;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if (user.getBirthDate().isBefore(LocalDate.now().minusYears(18))) {
            userPersistencePort.saveUser(user);
        } else {
            throw new UnderageException();
        }
    }

    @Override
    public String getRolName(Long id) {
        return userPersistencePort.getRolName(id);
    }

    @Override
    public String getPhoneNumber(Long id) {
        return userPersistencePort.getPhoneNumber(id);
    }

    @Override
    public String getEmail(Long id) {
        return userPersistencePort.getEmail(id);
    }


}
