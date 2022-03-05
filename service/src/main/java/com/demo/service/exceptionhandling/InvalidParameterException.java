package com.demo.service.exceptionhandling;

public class InvalidParameterException extends Exception {
    public InvalidParameterException(String message) {
        super(message);
    }
}
