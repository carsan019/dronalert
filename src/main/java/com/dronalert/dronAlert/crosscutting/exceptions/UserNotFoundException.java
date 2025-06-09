package com.dronalert.dronAlert.crosscutting.exceptions;

/*
Excepción lanzada cuando el usuario no se encuentra en la base de datos.
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
