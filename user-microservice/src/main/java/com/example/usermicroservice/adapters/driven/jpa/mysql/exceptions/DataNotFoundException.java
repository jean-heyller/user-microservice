package com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message) {
        super(message);
    }
}
