package com.dronalert.dronAlert.domain.jwt;

/*
Objeto de respuesta para errores que se retornan al usuario.
*/

public class ResponseErrorDomain {

    private String errorMessage;

    public ResponseErrorDomain() {}

    public ResponseErrorDomain(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Getters y Setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}