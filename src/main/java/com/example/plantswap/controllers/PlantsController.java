package com.example.plantswap.controllers;

import com.example.plantswap.repo.PlantsRepo;
import com.example.plantswap.models.Plants;
import com.example.plantswap.services.PlantServices;

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
    private PlantServices plantServices;

    @PostMapping
    public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
        Plants createdPlant = plantServices.createPlant(plant);
        return new ResponseEntity<>(createdPlant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Plants>> getAllPlants() {
        List<Plants> plant = plantServices.getAllPlants();
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Plants>> getPlantByName(@PathVariable String name) {
        List<Plants> plant = plantServices.getPlantByName(name);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Plants>> getPlantByDifficulty(@PathVariable int difficulty) {
        List<Plants> plant = plantServices.getPlantByDifficulty(difficulty);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Plants>> getPlantByAge(@PathVariable double age) {
        List<Plants> plant = plantServices.getPlantByAge(age);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Plants>> getPlantByStatus(@PathVariable String status) {
        List<Plants> plant = plantServices.getPlantByStatus(status);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Plants>> getPlantByPriceRange(@RequestParam int minPrice, @RequestParam int maxPrice) {
        List<Plants> plant = plantServices.getPlantByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

/*    @GetMapping("/status")
    public ResponseEntity<List<Plants>> getStatusPlants(@RequestParam String status) {
        List<Plants> plant = plantServices.getAllPlantsStatus();
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plants> updatePlant(@PathVariable String id, @RequestBody Plants plant) {
        Plants existingPlant = plantServices.findById(id)
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
    }*/

}



