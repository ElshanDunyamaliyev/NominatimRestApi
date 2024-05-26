package com.example.resttomcathelloworld.exception;

public class InvalidDbCredentialsException extends RuntimeException{
    public InvalidDbCredentialsException(String message) {
        super(message);
    }
}
