package com.dronalert.dronAlert.repository;

import com.dronalert.dronAlert.domain.DroneDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DroneDomainRepository extends JpaRepository<DroneDomain, UUID> {

    Optional<DroneDomain> findByDeviceIdentifier(String deviceIdentifier);
}
