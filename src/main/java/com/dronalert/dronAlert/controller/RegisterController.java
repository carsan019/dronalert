package com.dronalert.dronAlert.controller;

import com.dronalert.dronAlert.domain.jwt.JwtRequestDomain;
import com.dronalert.dronAlert.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService RegisterService;

    @Autowired
    public RegisterController(RegisterService userRegisterService) {
        this.RegisterService = userRegisterService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param userRequest Objeto JSON que representa los datos del usuario a registrar.
     * @return Respuesta con el usuario creado o error si falla la validación.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody JwtRequestDomain userRequest) {
        logger.info("Solicitud de registro recibida para el email: {}", userRequest.getEmail());

        if (userRequest.getEmail() == null || userRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"El email y la contraseña son obligatorios\"}");
        }

        try {
            RegisterService.registerUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");

        } catch (RuntimeException ex) {
            logger.error("Error al registrar usuario: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"error\":\"" + ex.getMessage() + "\"}");
        }
    }
}