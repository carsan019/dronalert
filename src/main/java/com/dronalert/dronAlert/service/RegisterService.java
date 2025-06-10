package com.dronalert.dronAlert.service;

import com.dronalert.dronAlert.domain.jwt.JwtRequestDomain;
import com.dronalert.dronAlert.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(JwtRequestDomain userRequest) {
        // Validate email and password
        if (userRequest.getEmail() == null || userRequest.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        // Check if user already exists
        Optional<JwtRequestDomain> existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists.");
        }

        // Set ID and encrypt password
        userRequest.setId(UUID.randomUUID()); // Genera el UUID aquí
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // Save user to database
        userRepository.save(userRequest);
    }
}
