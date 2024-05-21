package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.domain.api.IUserServicePort;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {
    private final UserDetailServiceImpl userDetailService;
    private final IUserServicePort userServicePort;

    public UserValidationService(UserDetailServiceImpl userDetailService, IUserServicePort userServicePort) {
        this.userDetailService = userDetailService;
        this.userServicePort = userServicePort;
    }
    public void validateUser(Long rolId) {
        System.out.println(this.userDetailService.getAuthority());
    }

}
