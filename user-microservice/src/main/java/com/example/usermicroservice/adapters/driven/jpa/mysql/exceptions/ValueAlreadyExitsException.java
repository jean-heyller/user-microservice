package com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions;

public class ValueAlreadyExitsException extends RuntimeException{
    public ValueAlreadyExitsException(String message) {
        super(message);
    }
}
