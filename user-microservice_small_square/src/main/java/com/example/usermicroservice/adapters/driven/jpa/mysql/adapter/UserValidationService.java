package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataNotFoundException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.PermissionDeniedCreateUserException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driving.http.util.Rol;
import com.example.usermicroservice.domain.api.IUserServicePort;
import org.springframework.stereotype.Service;

import static com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl.ROL_NOT_FOUND;

@Service
public class UserValidationService {
    private final UserDetailServiceImpl userDetailService;
    private final IUserServicePort userServicePort;
    private final IRolRepository rolRepository;

    public UserValidationService(UserDetailServiceImpl userDetailService, IUserServicePort userServicePort,
                                 IRolRepository rolRepository) {
        this.userDetailService = userDetailService;
        this.userServicePort = userServicePort;
        this.rolRepository =  rolRepository;
    }

    public boolean validateUser(Long rolId) {
        RolEntity rolEntity = rolRepository.findById(rolId)
                .orElseThrow(() -> new DataNotFoundException(ROL_NOT_FOUND));

        String rol = rolEntity.getName().toUpperCase();

        String permission = this.userDetailService.getAuthority();

        Rol userRol = Rol.valueOf(permission);

        if(!userRol.allowed(rol)) {
            throw new PermissionDeniedCreateUserException();
        }else {
            return userRol.allowed(rol);
        }

    }


}
