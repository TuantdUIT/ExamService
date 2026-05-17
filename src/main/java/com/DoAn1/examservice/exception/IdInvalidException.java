package com.DoAn1.examservice.exception;

public class IdInvalidException extends RuntimeException {

    public IdInvalidException(String message) {
        super(message);
    }

    public IdInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}

