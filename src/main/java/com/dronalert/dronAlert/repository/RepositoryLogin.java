package com.dronalert.dronAlert.repository;

import com.dronalert.dronAlert.domain.jwt.JwtRequestDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
Repositorio para operaciones de acceso a datos relacionadas con la autenticación
 */
@Repository
public interface RepositoryLogin extends JpaRepository<JwtRequestDomain, UUID> {
    Optional<JwtRequestDomain> findByEmail(String email);
}