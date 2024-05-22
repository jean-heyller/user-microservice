package com.example.usermicroservice.adapters.driving.http.dto.request;

import com.example.usermicroservice.adapters.driving.http.util.AdapterConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddRolRequest {
        @NotBlank(message = AdapterConstants.FIELD_NAME_NULL_MESSAGE)
        @Size(max = 50, message = AdapterConstants.FIELD_NAME_SIZE_MESSAGE)
        private final String name;

        @NotBlank(message = AdapterConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
        @Size(max = 90, message = AdapterConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
        private final String description;
}
