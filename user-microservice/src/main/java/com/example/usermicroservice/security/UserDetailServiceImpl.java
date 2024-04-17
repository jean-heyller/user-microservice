package com.example.usermicroservice.security;

import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;

    public UserDetailServiceImpl(IUserEntityMapper userEntityMapper, IUserRepository userRepository) {
        this.userEntityMapper = userEntityMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .map(userEntityMapper::toModel)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDetailsImpl(user);
    }
}