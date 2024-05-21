package com.example.usermicroservice.adapters.driving.http.dto.request;

import com.example.usermicroservice.domain.model.Rol;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddUserRequestTest {
    private Long id = 1L;

    private final String name = "John";

    private final String lastName = "Doe";

    private final String email = "prueba@hotmail.com";

    private final String password = "password123";

    private final String identification = "1234567890";

    private final String phone = "+57 304 291 8990";

    private final LocalDate birthDate = LocalDate.of(1990, 12, 12);

    private final Long idRol = 1L;

    private final Rol rol = new Rol(1L, "ADMIN", "administrator");

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    void testInvalidName() {
        AddUserRequest request = new AddUserRequest("", lastName, identification, email, password, phone, birthDate, idRol);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testInvalidIdentification() {
        String oversizedIdentification = "1".repeat(51);
        AddUserRequest request = new AddUserRequest(name, lastName, oversizedIdentification, email, password, phone, birthDate, idRol);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("identification", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testInvalidPassword() {
        AddUserRequest request = new AddUserRequest(name, lastName, identification, email, "short", phone, birthDate, idRol);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("password", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testInvalidBirthDate() {
        AddUserRequest request = new AddUserRequest(name, lastName, identification, email, password, phone, null, idRol);
        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("birthDate", violations.iterator().next().getPropertyPath().toString());
    }



}