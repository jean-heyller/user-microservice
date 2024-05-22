package com.example.usermicroservice.adapters.driving.http.mapper;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddRolRequest;
import com.example.usermicroservice.domain.model.Rol;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T08:39:25-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IRolRequestMapperImpl implements IRolRequestMapper {

    @Override
    public Rol addRequestToRol(AddRolRequest rolRequest) {
        if ( rolRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = rolRequest.getName();
        description = rolRequest.getDescription();

        Long id = null;

        Rol rol = new Rol( id, name, description );

        return rol;
    }
}
