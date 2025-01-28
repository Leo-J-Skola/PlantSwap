package com.example.plantswap.controllers;

import com.example.plantswap.repo.PlantsRepo;
import com.example.plantswap.models.Plants;
import com.example.plantswap.services.PlantServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plants")
public class PlantsController {

    @Autowired
    private PlantServices plantServices;

    @PostMapping
    public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
        Plants createdPlant = plantServices.createPlant(plant);
        return new ResponseEntity<>(createdPlant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plants> deletePlant(@PathVariable Plants id) {
        plantServices.deletePlant(id);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);  //I found some more fitting error codes from the HttpStatus.class
                                                                // since i didnt want to spend too much time
                                                                // fixing the jakarta.validation libraries to make custom error messages
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Plants> updatePlant(@RequestBody Plants id) {
        Plants updatedPlant = plantServices.updatePlant(id);
        return new ResponseEntity<>(updatedPlant, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Plants>> getAllPlants() {
        List<Plants> plant = plantServices.getAllPlants();
        return new ResponseEntity<>(plant, HttpStatus.FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Plants>> getPlantById(@PathVariable String id) {
        Optional<Plants> plant = plantServices.getPlantById(id);
        return new ResponseEntity<>(plant, HttpStatus.FOUND);
    }

}



