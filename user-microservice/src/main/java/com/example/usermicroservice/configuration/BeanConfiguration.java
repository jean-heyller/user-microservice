package com.example.usermicroservice.configuration;

import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.RolAdapter;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.domain.api.IRolServicePort;
import com.example.usermicroservice.domain.api.usecase.RolUseCase;
import com.example.usermicroservice.domain.spi.IRolPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRolRepository rolRepository;

    private final IRolEntityMapper rolEntityMapper;

    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolAdapter(rolRepository,rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort(){
        return new RolUseCase(rolPersistencePort());
    }


}
