package com.example.usermicroservice.adapters.driven.jpa.mysql.mapper;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.domain.model.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRolEntityMapper {
    RolEntity toEntity(Rol rol);
    Rol toModel(RolEntity rolEntity);
}
