package com.example.usermicroservice.adapters.driven.jpa.mysql.mapper;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.domain.model.Rol;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T08:39:25-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IRolEntityMapperImpl implements IRolEntityMapper {

    @Override
    public RolEntity toEntity(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolEntity.RolEntityBuilder rolEntity = RolEntity.builder();

        rolEntity.id( rol.getId() );
        rolEntity.name( rol.getName() );
        rolEntity.description( rol.getDescription() );

        return rolEntity.build();
    }

    @Override
    public Rol toModel(RolEntity rolEntity) {
        if ( rolEntity == null ) {
            return null;
        }

        String name = null;
        Long id = null;
        String description = null;

        name = rolEntity.getName();
        id = rolEntity.getId();
        description = rolEntity.getDescription();

        Rol rol = new Rol( id, name, description );

        return rol;
    }
}
