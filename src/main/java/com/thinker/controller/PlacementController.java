package com.thinker.controller;

import com.thinker.entity.Placement;
import com.thinker.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/placements")
@CrossOrigin(origins = "*")
public class PlacementController {

    private static final Logger logger = LoggerFactory.getLogger(PlacementController.class);

    @Autowired
    private PlacementService placementService;

    @GetMapping
    public ResponseEntity<List<Placement>> getAllPlacements() {
        logger.info("Fetching all placements");
        return ResponseEntity.ok(placementService.getAllPlacements());
    }

    @PostMapping
    public ResponseEntity<Placement> createPlacement(@RequestBody Placement placement) {
        logger.info("Creating new placement: {}", placement.getName());
        return ResponseEntity.ok(placementService.savePlacement(placement));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placement> getPlacementById(@PathVariable Long id) {
        logger.info("Fetching placement with id: {}", id);
        return placementService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placement> updatePlacement(@PathVariable Long id, @RequestBody Placement placementDetails) {
        logger.info("Updating placement with id: {}", id);
        return placementService.findById(id)
                .map(placement -> {
                    placement.setName(placementDetails.getName());
                    placement.setCompany(placementDetails.getCompany());
                    placement.setSalary(placementDetails.getSalary());
                    placement.setPosition(placementDetails.getPosition());
                    placement.setLocation(placementDetails.getLocation());
                    return ResponseEntity.ok(placementService.savePlacement(placement));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlacement(@PathVariable Long id) {
        logger.info("Deleting placement with id: {}", id);
        return placementService.findById(id)
                .map(placement -> {
                    placementService.deletePlacement(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}