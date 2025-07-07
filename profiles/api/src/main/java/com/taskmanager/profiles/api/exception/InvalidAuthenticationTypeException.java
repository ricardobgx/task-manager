package com.taskmanager.profiles.api.exception;

public class InvalidAuthenticationTypeException extends RuntimeException {
    public InvalidAuthenticationTypeException(String message) {
        super(message);
    }
}
