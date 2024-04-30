package com.example.usermicroservice.adapters.driving.http.util;

public class AdapterConstants {

    private AdapterConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum field{
        NAME,
        DESCRIPTION,

    }

    public static final String FIELD_NAME_NULL_MESSAGE = "`name` cannot be null";

    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "`description` cannot be null";

    public static final String FIELD_DESCRIPTION_SIZE_MESSAGE = "`description` cannot be greater than 90 characters";

    public static final String FIELD_NAME_SIZE_MESSAGE = "`name` cannot be greater than 50 characters";

    public static final String FIELD_LAST_NAME_NULL_MESSAGE = "`last name` cannot be null";

    public static final String FIELD_LAST_NAME_SIZE_MESSAGE = "`last name` cannot be greater than 50 characters";

    public static final String FIELD_IDENTIFICATION_NULL_MESSAGE = "`identification` cannot be null";

    public static final String FIELD_IDENTIFICATION_SIZE_MESSAGE = "`identification` cannot be greater than 50 characters";

    public static final String FIELD_EMAIL_NULL_MESSAGE = "`email` cannot be null";

    public static final String FIELD_EMAIL_SIZE_MESSAGE = "`email` cannot be greater than 50 characters";

    public static final String FIELD_EMAIL_VALID_MESSAGE = "`email` should be valid";

    public static final String FIELD_PASSWORD_NULL_MESSAGE = "`password` cannot be null";

    public static final String FIELD_PASSWORD_SIZE_MESSAGE = "`password` cannot be greater than 50 characters";

    public static final String FIELD_ROLE_NULL_MESSAGE = "`role` cannot be null";

    public static final String ROL_AMIN = "ROLE_ADMIN";

    public static final String ROL_TUTOR = "ROLE_TUTOR";


}
