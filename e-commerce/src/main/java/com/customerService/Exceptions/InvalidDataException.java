package com.customerService.Exceptions;

public class InvalidDataException extends RuntimeException {

    private final String errorDescription;

    public InvalidDataException(String message) {
        this.errorDescription = message;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
