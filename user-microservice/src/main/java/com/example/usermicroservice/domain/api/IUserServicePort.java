package com.example.usermicroservice.domain.api;

import com.example.usermicroservice.domain.model.User;

public interface IUserServicePort {

        public void saveUser(User user);
}
