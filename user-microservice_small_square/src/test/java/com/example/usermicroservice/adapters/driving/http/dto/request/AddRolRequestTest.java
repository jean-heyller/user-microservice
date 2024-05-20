package com.example.usermicroservice.adapters.driving.http.dto.request;

import static org.junit.jupiter.api.Assertions.*;


import com.example.usermicroservice.adapters.driving.http.util.AdapterConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


class AddRolRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testValidationNotBlank() {
        AddRolRequest addRolRequest = new AddRolRequest("", "");
        Set<ConstraintViolation<AddRolRequest>> violations = validator.validate(addRolRequest);
        assertEquals(2, violations.size());
        for(ConstraintViolation<AddRolRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(AdapterConstants.FIELD_NAME_NULL_MESSAGE) ||
                    violation.getMessage().contains(AdapterConstants.FIELD_DESCRIPTION_NULL_MESSAGE));
        }
    }


    @Test
    void testValidationMaxSize() {
        String longName = "name";
        String longDescription = "a".repeat(91);
        AddRolRequest addRolRequest = new AddRolRequest(longName, longDescription);
        Set<ConstraintViolation<AddRolRequest>> violations = validator.validate(addRolRequest);
        assertEquals(1, violations.size());
        for (ConstraintViolation<AddRolRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(AdapterConstants.FIELD_DESCRIPTION_SIZE_MESSAGE));
        }
    }


    @Test
    void testValidationMaxSizeName() {
        String longName = "a".repeat(51);
        String description = "Valid Description";
        AddRolRequest addRolRequest = new AddRolRequest(longName, description);
        Set<ConstraintViolation<AddRolRequest>> violations = validator.validate(addRolRequest);
        assertEquals(1, violations.size());
        for (ConstraintViolation<AddRolRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(AdapterConstants.FIELD_NAME_SIZE_MESSAGE));
        }
    }
}