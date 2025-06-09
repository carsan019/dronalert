package com.dronalert.dronAlert.crosscutting.exceptions;

/*
Excepción lanzada cuando la contraseña ingresada no es correcta.
 */

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}