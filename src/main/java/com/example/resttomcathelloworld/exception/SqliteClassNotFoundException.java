package com.example.resttomcathelloworld.exception;

public class SqliteClassNotFoundException extends RuntimeException{
    public SqliteClassNotFoundException(String message) {
        super(message);
    }
}
