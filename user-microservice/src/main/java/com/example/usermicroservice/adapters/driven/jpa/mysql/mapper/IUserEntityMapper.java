package com.example.usermicroservice.adapters.driven.jpa.mysql.mapper;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    User toModel(UserEntity userEntity);

    UserEntity toEntity(User user);
}
