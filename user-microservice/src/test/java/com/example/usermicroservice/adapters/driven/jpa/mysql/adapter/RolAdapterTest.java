package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;



import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.ValueAlreadyExitsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.mapper.IRolEntityMapper;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.domain.model.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RolAdapterTest {

    @InjectMocks
    RolAdapter rolAdapter;

    @Mock
    IRolRepository rolRepository;

    @Mock
    IRolEntityMapper rolEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRol() {
        Rol rol = new Rol(1L, "Test Role", "Test Description");
        when(rolRepository.findByName(rol.getName().toLowerCase())).thenReturn(Optional.empty());

        rolAdapter.saveRol(rol);

        verify(rolRepository, times(1)).save(any());
    }

    @Test
    void testSaveRolThrowsException() {
        Rol rol = new Rol(1L, "Test Role", "Test Description");
        RolEntity rolEntity = new RolEntity(1L, "Test Role", "Test Description");
        when(rolRepository.findByName(rol.getName().toLowerCase())).thenReturn(Optional.of(rolEntity));

        assertThrows(ValueAlreadyExitsException.class, () -> rolAdapter.saveRol(rol));
    }
}