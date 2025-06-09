package com.dronalert.dronAlert.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "drones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "drone_id")
    private UUID droneId;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "estimated_range")
    private Double estimatedRange; // En metros

    @Column(name = "device_identifier", unique = true)
    private String deviceIdentifier;  // Dirección MAC u otro identificador único

    @Column(name = "description")
    private String description;
}
