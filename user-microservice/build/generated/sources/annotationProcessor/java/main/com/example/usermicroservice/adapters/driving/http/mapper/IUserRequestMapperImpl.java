package com.example.usermicroservice.adapters.driving.http.mapper;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.domain.model.Rol;
import com.example.usermicroservice.domain.model.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T08:39:25-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public User addRequestToUser(AddUserRequest addUserRequest) {
        if ( addUserRequest == null ) {
            return null;
        }

        Rol rol = null;
        String name = null;
        String lastName = null;
        String email = null;
        String password = null;
        LocalDate birthDate = null;
        String identification = null;
        String phone = null;

        rol = addUserRequestToRol( addUserRequest );
        name = addUserRequest.getName();
        lastName = addUserRequest.getLastName();
        email = addUserRequest.getEmail();
        password = addUserRequest.getPassword();
        birthDate = addUserRequest.getBirthDate();
        identification = addUserRequest.getIdentification();
        phone = addUserRequest.getPhone();

        Long id = null;

        User user = new User( id, name, lastName, email, password, identification, rol, phone, birthDate );

        return user;
    }

    protected Rol addUserRequestToRol(AddUserRequest addUserRequest) {
        if ( addUserRequest == null ) {
            return null;
        }

        Long id = null;

        id = addUserRequest.getRolId();

        String name = "rolName";
        String description = "rolDescription";

        Rol rol = new Rol( id, name, description );

        return rol;
    }
}
