package com.dronalert.dronAlert.service;

import com.dronalert.dronAlert.domain.DroneDomain;
import com.dronalert.dronAlert.domain.DroneEventDomain;
import com.dronalert.dronAlert.repository.DroneDomainRepository;
import com.dronalert.dronAlert.repository.DroneEventRepository;
import com.dronalert.dronAlert.dto.DroneEventRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DroneEventService {

    private final DroneDomainRepository droneRepository;
    private final DroneEventRepository droneEventRepository;

    public DroneEventService(DroneDomainRepository droneRepository, DroneEventRepository droneEventRepository) {
        this.droneRepository = droneRepository;
        this.droneEventRepository = droneEventRepository;
    }

    public void registerEvent(DroneEventRequest request) {
        DroneDomain associatedDrone = null;

        if (!request.getIsEncrypted() && request.getDeviceIdentifier() != null) {
            Optional<DroneDomain> existingDrone = droneRepository.findByDeviceIdentifier(request.getDeviceIdentifier());

            if (existingDrone.isPresent()) {
                associatedDrone = existingDrone.get();
            } else {
                // Crear nuevo dron si no existe
                DroneDomain newDrone = new DroneDomain();
                newDrone.setDeviceIdentifier(request.getDeviceIdentifier());
                newDrone.setModel(request.getModel() != null ? request.getModel() : "Desconocido");
                newDrone.setEstimatedRange(request.getEstimatedRange());
                newDrone.setDescription("Dron detectado por primera vez automáticamente.");
                associatedDrone = droneRepository.save(newDrone);
            }
        }

        DroneEventDomain event = new DroneEventDomain();
        event.setDrone(associatedDrone);
        event.setDeviceIdentifier(request.getDeviceIdentifier());
        event.setFrequency(request.getFrequency());
        event.setSignalStrength(request.getSignalStrength());
        event.setIsEncrypted(request.getIsEncrypted());
        event.setLatitude(request.getLatitude());
        event.setLongitude(request.getLongitude());
        event.setLocationDescription(request.getLocationDescription());
        event.setDescription(request.getDescription());
        event.setReportedAt(LocalDateTime.now());

        droneEventRepository.save(event);
    }
    public List<DroneEventDomain> getAllEvents() {
        return droneEventRepository.findAll();
    }

}
