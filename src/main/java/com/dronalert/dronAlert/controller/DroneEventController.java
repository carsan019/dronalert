package com.dronalert.dronAlert.controller;

import com.dronalert.dronAlert.domain.DroneEventDomain;
import com.dronalert.dronAlert.dto.DroneEventRequest;
import com.dronalert.dronAlert.service.DroneEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class DroneEventController {

    private final DroneEventService droneEventService;

    public DroneEventController(DroneEventService droneEventService) {
        this.droneEventService = droneEventService;
    }

    // POST: registrar evento
    @PostMapping
    public ResponseEntity<String> registerDroneEvent(@RequestBody DroneEventRequest request) {
        try {
            droneEventService.registerEvent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el evento.");
        }
    }

    // GET: listar todos los eventos
    @GetMapping
    public ResponseEntity<List<DroneEventDomain>> getAllEvents() {
        List<DroneEventDomain> events = droneEventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}
