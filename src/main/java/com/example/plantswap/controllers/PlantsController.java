package com.example.plantswap.controllers;

import com.example.plantswap.repo.PlantsRepo;
import com.example.plantswap.models.Plants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantsController {

    @Autowired
    private PlantsRepo plantsRepo;

    @PostMapping
    public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
        Plants savedPlant = plantsRepo.save(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);
    }

    @GetMapping
    public ResponseEntity<List<Plants>> getAllPlants() {
        List<Plants> plants = plantsRepo.findAll();
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plants> getPlantById(@PathVariable String id) {
        Plants plant = plantsRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return ResponseEntity.ok(plant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plants> updatePlant(@PathVariable String id, @RequestBody Plants plant) {
        Plants existingPlant = plantsRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found."));

        if (plant.getName() != null) {
            existingPlant.setName(plant.getName());
        }

        if (plant.getScientificName() != null) {
            existingPlant.setScientificName(plant.getScientificName());
        }

        if (plant.getAge() != 0) {
            existingPlant.setAge(plant.getAge());
        }

        if (plant.getType() != null) {
            existingPlant.setType(plant.getType());
        }

        if (plant.getLightReq() != null) {
            existingPlant.setLightReq(plant.getLightReq());
        }

        if (plant.getWaterReq() != null) {
            existingPlant.setWaterReq(plant.getWaterReq());
        }

        if (plant.getDifficulty() != 0) {
            existingPlant.setDifficulty(plant.getDifficulty());
        }

        if (plant.getTradeOrSell() != null) {
            existingPlant.setTradeOrSell(plant.getTradeOrSell());
        }

        if (plant.getPrice() != 0) {
            existingPlant.setPrice(plant.getPrice());
        }

        if (plant.getImages() != null) {
            existingPlant.setImages(plant.getImages());
        }

        if (plant.getStatus() != null) {
            existingPlant.setStatus(plant.getStatus());
        }

        if (plant.getOwnerId() != null) {
            existingPlant.setOwnerId(plant.getOwnerId());

        }

        Plants updatedPlant = plantsRepo.save(existingPlant);
        return ResponseEntity.ok(updatedPlant);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable String id) {
        if(!plantsRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
        }
        plantsRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}



