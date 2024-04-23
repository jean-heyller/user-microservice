package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.Service.UserDetailServiceImpl;
import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.example.usermicroservice.adapters.driving.http.dto.response.AuthResponse;
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
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@PreAuthorize("permitAll()")
public class AuthenticactionController {


    private final UserDetailServiceImpl userDetailService;



    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);

    }

//    @PostMapping("/sign up")
//    public ResponseEntity<Void> signUp(@RequestBody @Valid AddUserRequest userRequest){
//        this.userDetailService.createUser(userRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }




}
