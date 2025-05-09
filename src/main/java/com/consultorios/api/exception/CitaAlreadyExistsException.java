package com.consultorios.api.exception;

public class CitaAlreadyExistsException extends RuntimeException {
    public CitaAlreadyExistsException(String message) {
        super(message);
    }
}
