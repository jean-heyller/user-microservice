package com.example.usermicroservice.configuration;

import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.RolAdapter;
import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.domain.api.IRolServicePort;
import com.example.usermicroservice.domain.api.IUserServicePort;
import com.example.usermicroservice.domain.api.usecase.RolUseCase;
import com.example.usermicroservice.domain.api.usecase.UserUseCase;
import com.example.usermicroservice.domain.spi.IRolPersistencePort;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRolRepository rolRepository;

    private final IRolEntityMapper rolEntityMapper;

    private final IUserRepository userRepository;

    private final IUserEntityMapper userEntityMapper;





    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolAdapter(rolRepository,rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort(){
        return new RolUseCase(rolPersistencePort());
    }



    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepository,rolRepository,rolEntityMapper,userEntityMapper,new BCryptPasswordEncoder());
    }





    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }



}
