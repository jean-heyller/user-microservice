package com.example.usermicroservice.adapters.driving.http.controller;

import com.example.usermicroservice.adapters.driving.http.dto.request.AddRolRequest;
import com.example.usermicroservice.adapters.driving.http.mapper.IRolRequestMapper;
import com.example.usermicroservice.domain.api.IRolServicePort;
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
    @Operation(summary = "Add a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddRolRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid role data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Role already exists",
                    content = @Content) })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Void> addRol(@Valid @RequestBody AddRolRequest request){
        rolServicePort.saveRol(rolRequestMapper.addRequestToRol(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
