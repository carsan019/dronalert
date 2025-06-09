package com.dronalert.dronAlert.repository;

import com.dronalert.dronAlert.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDomain, UUID> {
    Optional<UserDomain> findByEmail(String email);
}
