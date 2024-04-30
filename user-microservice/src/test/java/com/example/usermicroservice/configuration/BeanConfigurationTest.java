package com.example.usermicroservice.configuration;


import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.domain.api.IRolServicePort;
import com.example.usermicroservice.domain.api.IUserServicePort;

import com.example.usermicroservice.domain.spi.IRolPersistencePort;
import com.example.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertNotNull;


 class BeanConfigurationTest {

    @Mock
    private IRolRepository rolRepository;

    @Mock
    private IRolEntityMapper rolEntityMapper;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private BeanConfiguration beanConfiguration;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testRolPersistencePort() {
        IRolPersistencePort rolPersistencePort = beanConfiguration.rolPersistencePort();
        assertNotNull(rolPersistencePort);
    }

    @Test
     void testRolServicePort() {
        IRolServicePort rolServicePort = beanConfiguration.rolServicePort();
        assertNotNull(rolServicePort);
    }

    @Test
     void testUserPersistencePort() {
        IUserPersistencePort userPersistencePort = beanConfiguration.userPersistencePort();
        assertNotNull(userPersistencePort);
    }

    @Test
     void testUserServicePort() {
        IUserServicePort userServicePort = beanConfiguration.userServicePort();
        assertNotNull(userServicePort);
    }
}