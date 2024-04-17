package com.example.usermicroservice.adapters.driving.http.mapper;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol.name", constant = "rolName")
    @Mapping(target = "rol.id", source = "rolId")
    @Mapping(target = "rol.description", constant = "rolDescription")
    User addRequestToUser(AddUserRequest addUserRequest);


}
