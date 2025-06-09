package com.dronalert.dronAlert.crosscutting.exceptions;

import java.util.regex.Pattern;

public class EmailValidator {
    // Constructor privado para evitar instanciación.
    private  EmailValidator(){

    }
    // Patrón precompilado.
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    // Metodo de validación.
    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
