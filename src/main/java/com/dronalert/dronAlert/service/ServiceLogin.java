package com.dronalert.dronAlert.service;


import com.dronalert.dronAlert.domain.jwt.JwtRequestDomain;
import com.dronalert.dronAlert.domain.jwt.JwtResponseDomain;
import com.dronalert.dronAlert.crosscutting.exceptions.EmailValidator;
import com.dronalert.dronAlert.crosscutting.exceptions.InvalidCredentialsException;
import com.dronalert.dronAlert.crosscutting.exceptions.UserNotFoundException;
import com.dronalert.dronAlert.repository.RepositoryLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio encargado de la autenticación de usuarios.
 * Incluye validación del formato del correo y verificación de credenciales.
 */
@Service
public class ServiceLogin {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RepositoryLogin repositoryLogin;

    @Autowired
    public ServiceLogin(RepositoryLogin repositoryLogin) {
        this.repositoryLogin = repositoryLogin;
    }

    public JwtResponseDomain authenticateUser(JwtRequestDomain authenticationRequest) {
        // Validar formato del correo utilizando EmailValidator
        if (!EmailValidator.validateEmail(authenticationRequest.getEmail())) {
            throw new IllegalArgumentException("Formato de correo inválido");
        }

        // Buscar el usuario en la base de datos
        Optional<JwtRequestDomain> optionalUser = repositoryLogin.findByEmail(authenticationRequest.getEmail());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
        JwtRequestDomain user = optionalUser.get();

        // Validar la contraseña
        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Datos no son correctos");
        }

        // Retorna el token (aquí se debería generar un token real)
        return new JwtResponseDomain("111");
    }
}
