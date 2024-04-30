package com.example.usermicroservice.adapters.driving.http.mapper;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddRolRequest;
import com.example.usermicroservice.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRolRequestMapper {
    @Mapping(target = "id", ignore = true)
    Rol addRequestToRol(AddRolRequest rolRequest);
}
