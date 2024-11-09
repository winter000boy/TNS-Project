package com.example.demo.service;

import com.example.demo.entity.Placement;
import com.example.demo.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacementService {

    @Autowired
    private PlacementRepository placementRepository;

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    public Placement savePlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    public Optional<Placement> findById(Long id) {
        return placementRepository.findById(id);
    }

    public void deleteById(Long id) {
        placementRepository.deleteById(id);
    }
}
