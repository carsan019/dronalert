package com.dronalert.dronAlert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneEventRequest {

    private Double frequency;

    private Double signalStrength;
    private Boolean isEncrypted;

    private String deviceIdentifier;

    private Double latitude;

    private Double longitude;

    private String locationDescription;

    private String description;

    private String model;

    private Double estimatedRange;
}
