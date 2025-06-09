package com.dronalert.dronAlert.controller;

import com.dronalert.dronAlert.domain.jwt.JwtRequestDomain;
import com.dronalert.dronAlert.domain.jwt.JwtResponseDomain;
import com.dronalert.dronAlert.domain.jwt.ResponseErrorDomain;
import com.dronalert.dronAlert.crosscutting.exceptions.InvalidCredentialsException;
import com.dronalert.dronAlert.crosscutting.exceptions.UserNotFoundException;
import com.dronalert.dronAlert.service.ServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JwtAuthenticationController {

    private final ServiceLogin serviceLogin;

    @Autowired
    public JwtAuthenticationController(ServiceLogin serviceLogin) {
        this.serviceLogin = serviceLogin;
    }
    @PostMapping(value = "/authenticate")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequestDomain authenticationRequest) {
        try {
            JwtResponseDomain jwtResponse = serviceLogin.authenticateUser(authenticationRequest);
            return ResponseEntity.ok(jwtResponse); // Retorna el token JWT
        } catch (IllegalArgumentException ex) {
            ResponseErrorDomain error = new ResponseErrorDomain(ex.getMessage());
            // Error en el formato del correo
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); // 400
        } catch (UserNotFoundException ex) {
            ResponseErrorDomain error = new ResponseErrorDomain(ex.getMessage());
            // Usuario no encontrado en la base de datos
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404
        } catch (InvalidCredentialsException ex) {
            ResponseErrorDomain error = new ResponseErrorDomain(ex.getMessage());
            // Contraseña incorrecta
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error); // 401
        } catch (Exception ex) {
            // Error interno del servidor
            ResponseErrorDomain error = new ResponseErrorDomain("Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // 500
        }
    }
}
