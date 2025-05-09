package com.consultorios.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Constructor que recibe un mensaje como parámetro
    public ResourceNotFoundException(String message) {
        super(message);  // Llama al constructor de la clase RuntimeException con el mensaje
    }
}
