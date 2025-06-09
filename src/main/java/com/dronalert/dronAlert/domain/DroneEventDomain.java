package com.dronalert.dronAlert.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "drone_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneEventDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private UUID eventId;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = true)
    private DroneDomain drone;

    @Column(name = "frequency", nullable = false)
    private Double frequency;

    @Column(name = "signal_strength")
    private Double signalStrength;

    @Column(name = "is_encrypted")
    private Boolean isEncrypted;

    @Column(name = "device_identifier")
    private String deviceIdentifier;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "location_description")
    private String locationDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt;
}
