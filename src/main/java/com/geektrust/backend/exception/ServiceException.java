package com.geektrust.backend.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

}
