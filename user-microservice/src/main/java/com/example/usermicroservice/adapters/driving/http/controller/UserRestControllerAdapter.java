package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.UserDetailServiceImpl;
import com.example.usermicroservice.adapters.driven.jpa.mysql.adapter.UserValidationService;
import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.adapters.driving.http.dto.response.RolNameResponse;
import com.example.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.usermicroservice.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_OWNER')")
public class UserRestControllerAdapter {

    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;

    private final UserDetailServiceImpl userDetailService;

    private final UserValidationService userValidationService;



    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddUserRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid user data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content) })
    @PostMapping("/register")
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request){
        this.userValidationService.validateUser(request.getRolId());
        userServicePort.saveUser(userRequestMapper.addRequestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getName")
    @PreAuthorize("permitAll()")
    public ResponseEntity<RolNameResponse> getRolName(@RequestParam Long id){
        return ResponseEntity.ok(new RolNameResponse(userServicePort.getRolName(id)));
    }

    @GetMapping("/getPhoneNumber")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> getPhoneNumber(@RequestParam Long id){
        return ResponseEntity.ok(userServicePort.getPhoneNumber(id));
    }

    @GetMapping("/getEmail")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> getEmail(@RequestParam Long id){
        return ResponseEntity.ok(userServicePort.getEmail(id));
    }

}
