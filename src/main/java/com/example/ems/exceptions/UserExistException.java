package com.example.ems.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
