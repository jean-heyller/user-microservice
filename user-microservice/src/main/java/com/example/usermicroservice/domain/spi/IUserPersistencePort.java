package com.example.usermicroservice.domain.spi;

import com.example.usermicroservice.domain.model.User;

public interface IUserPersistencePort {

    public void saveUser(User user);

    public String getRolName(Long id);

    public String getPhoneNumber(Long id);


    public String getEmail(Long id);

}
