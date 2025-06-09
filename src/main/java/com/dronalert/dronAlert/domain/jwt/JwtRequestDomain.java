package com.dronalert.dronAlert.domain.jwt;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")  // Mapea la tabla "users" de la base de datos
public class JwtRequestDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Generación automática de UUID
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "email")     // Credencial de autenticación: email
    private String email;

    @Column(name = "password")  // Credencial de autenticación: contraseña (debe estar encriptada)
    private String password;

    public JwtRequestDomain() {}  // Constructor vacío requerido por JPA

    // Constructor para inicialización manual
    public JwtRequestDomain(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public UUID getId() { return id;}
    public void setId(UUID  id) {
        this.id = id;
    }
    public String getEmail() { return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }
}
