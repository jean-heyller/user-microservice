package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddRolRequest;
import com.example.usermicroservice.adapters.driving.http.mapper.IRolRequestMapper;
import com.example.usermicroservice.domain.api.IRolServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
@Validated
@PreAuthorize("denyAll()")
public class RolRequestControllerAdapter {

    private final IRolRequestMapper rolRequestMapper;
    private final IRolServicePort rolServicePort;
    @PreAuthorize("hasAnyAuthority('READ')")
    @PostMapping("/")
    public ResponseEntity<Void> addRol(@Valid @RequestBody AddRolRequest request){
        rolServicePort.saveRol(rolRequestMapper.addRequestToRol(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
