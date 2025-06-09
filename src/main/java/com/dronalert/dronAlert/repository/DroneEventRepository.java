package com.dronalert.dronAlert.repository;

import com.dronalert.dronAlert.domain.DroneEventDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DroneEventRepository extends JpaRepository<DroneEventDomain, UUID> {
}
