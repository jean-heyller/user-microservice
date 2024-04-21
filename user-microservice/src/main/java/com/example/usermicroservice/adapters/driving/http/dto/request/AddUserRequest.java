package com.example.usermicroservice.adapters.driving.http.dto.request;

import com.example.usermicroservice.adapters.driving.http.util.AdapterConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddUserRequest {
    @NotBlank(message = AdapterConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = 50, message = AdapterConstants.FIELD_NAME_SIZE_MESSAGE)
    private final String name;

    @NotBlank(message = AdapterConstants.FIELD_LAST_NAME_NULL_MESSAGE)
    @Size(max = 50, message = AdapterConstants.FIELD_LAST_NAME_SIZE_MESSAGE)
    private final String lastName;

    @NotBlank(message = AdapterConstants.FIELD_IDENTIFICATION_NULL_MESSAGE)
    @Size(max = 50, message = AdapterConstants.FIELD_IDENTIFICATION_SIZE_MESSAGE)
    private final String identification;

    @NotBlank(message = AdapterConstants.FIELD_EMAIL_NULL_MESSAGE)
    @Email(message = AdapterConstants.FIELD_EMAIL_VALID_MESSAGE)
    private final String email;

    @NotBlank(message = AdapterConstants.FIELD_PASSWORD_NULL_MESSAGE)
    @Size(min = 8, message = AdapterConstants.FIELD_PASSWORD_SIZE_MESSAGE)
    private final String password;

    @NotNull(message = AdapterConstants.FIELD_ROLE_NULL_MESSAGE)
    private final Long rolId;
}