package com.example.usermicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddUserRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidAddUserRequest() {
        AddUserRequest request = new AddUserRequest("John", "Doe", "1234567890", "john.doe@example.com", "password123", 1L);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidName() {
        AddUserRequest request = new AddUserRequest("", "Doe", "1234567890", "john.doe@example.com", "password123", 1L);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidLastName() {
        AddUserRequest request = new AddUserRequest("John", "", "1234567890", "john.doe@example.com", "password123", 1L);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidIdentification() {
        AddUserRequest request = new AddUserRequest("John", "Doe", "", "john.doe@example.com", "password123", 1L);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("identification", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidEmail() {
        AddUserRequest request = new AddUserRequest("John", "Doe", "1234567890", "invalid email", "password123", 1L);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("email", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidPassword() {
        AddUserRequest request = new AddUserRequest("John", "Doe", "1234567890", "john.doe@example.com", "short", 1L);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidRolId() {
        AddUserRequest request = new AddUserRequest("John", "Doe", "1234567890", "john.doe@example.com", "password123", null);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("rolId", violations.iterator().next().getPropertyPath().toString());
    }

}