package com.example.usermicroservice.configuration.exceptionhandler;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";
    public static final String MAX_CHAR_EXCEPTION_MESSAGE = "Field %s can not exceed %s characters";

    public static final String VALUE_ALREADY_EXISTS_EXCEPTION = " you want to create already exists";
}
