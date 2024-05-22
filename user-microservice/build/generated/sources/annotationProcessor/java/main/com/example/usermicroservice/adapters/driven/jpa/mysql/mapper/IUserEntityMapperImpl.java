package com.example.usermicroservice.adapters.driven.jpa.mysql.mapper;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.model.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T13:32:09-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Override
    public User toModel(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        String name = null;
        String lastName = null;
        String email = null;
        String password = null;
        LocalDate birthDate = null;
        Long id = null;
        String identification = null;
        Rol rol = null;
        String phone = null;

        name = userEntity.getName();
        lastName = userEntity.getLastName();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        birthDate = userEntity.getBirthDate();
        id = userEntity.getId();
        identification = userEntity.getIdentification();
        rol = rolEntityToRol( userEntity.getRol() );
        phone = userEntity.getPhone();

        User user = new User( id, name, lastName, email, password, identification, rol, phone, birthDate );

        return user;
    }

    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( user.getId() );
        userEntity.name( user.getName() );
        userEntity.lastName( user.getLastName() );
        userEntity.identification( user.getIdentification() );
        userEntity.email( user.getEmail() );
        userEntity.password( user.getPassword() );
        userEntity.phone( user.getPhone() );
        userEntity.birthDate( user.getBirthDate() );
        userEntity.rol( rolToRolEntity( user.getRol() ) );

        return userEntity.build();
    }

    protected Rol rolEntityToRol(RolEntity rolEntity) {
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

    protected RolEntity rolToRolEntity(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolEntity.RolEntityBuilder rolEntity = RolEntity.builder();

        rolEntity.id( rol.getId() );
        rolEntity.name( rol.getName() );
        rolEntity.description( rol.getDescription() );

        return rolEntity.build();
    }
}
