package com.example.plantswap.services;

import com.example.plantswap.models.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plantswap.models.Plants;
import com.example.plantswap.repo.PlantsRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlantServices {

    @Autowired
    private PlantsRepo plantsRepo;

    public Plants createPlant(Plants plant) {
        validatePlant(plant);

        return plantsRepo.save(plant);
    }

    public void deletePlant(Plants plant) {
        if (Objects.isNull(plant)) {
            throw new IllegalArgumentException("Plant cannot be null.");
        }
        plantsRepo.delete(plant);
    }

    public List<Plants> getAllPlants() {
        return plantsRepo.findAll();
    }

    public Optional<Plants> getPlantById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Plant id can not be empty or null.");
        }
        Optional<Plants> plants = plantsRepo.findById(id);
        if (plants.isEmpty()) {
            throw new NoSuchElementException("No plant found with id '" + id + "'.");
        }
        return plants;
    }

                    //updatePlant method is specifically for updating a plant
                    //It checks if the plant exists including all fields
                    //If you dont edit a field, it will not update it
                    //It makes it so you can edit specifically one field if you want to
                    //I found .orElseThrow(() -> new NoSuchElementException
                    //from the Basic_bookshop_api project you had made

    public Plants updatePlant(String id, Plants updatedPlant) {
        Plants existingPlant = plantsRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Plant with id " + id + " not found"));

        if (updatedPlant.getName() != null && !updatedPlant.getName().trim().isEmpty()) {
            existingPlant.setName(updatedPlant.getName());
        }
        if (updatedPlant.getScientificName() != null && !updatedPlant.getScientificName().trim().isEmpty()) {
            existingPlant.setScientificName(updatedPlant.getScientificName());
        }
        if (updatedPlant.getType() != null && !updatedPlant.getType().trim().isEmpty()) {
            existingPlant.setType(updatedPlant.getType());
        }
        if (updatedPlant.getLightReq() != null && !updatedPlant.getLightReq().trim().isEmpty()) {
            existingPlant.setLightReq(updatedPlant.getLightReq());
        }
        if (updatedPlant.getWaterReq() != null && !updatedPlant.getWaterReq().trim().isEmpty()) {
            existingPlant.setWaterReq(updatedPlant.getWaterReq());
        }
        if (updatedPlant.getImages() != null && !updatedPlant.getImages().isEmpty()) {
            existingPlant.setImages(updatedPlant.getImages());
        }
        if (updatedPlant.getAge() <= 100 && updatedPlant.getAge() >= 0) {
            existingPlant.setAge(updatedPlant.getAge());
        }
        if (updatedPlant.getDifficulty() <= 10 && updatedPlant.getDifficulty() >= 1) {
            existingPlant.setDifficulty(updatedPlant.getDifficulty());
        }
        return plantsRepo.save(existingPlant);
    }

    //This makes sure a user cannot create a plant with invalid information.

    private void validatePlant(Plants plant) { //This one is specifically for creating a plant
        if (plant.getName() == null || plant.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The plant name can not be empty or null.");
        }

        if (plant.getScientificName() == null || plant.getScientificName().trim().isEmpty()) {
            throw new IllegalArgumentException("The scientific name can not be empty or null.");
        }

        if (plant.getType() == null || plant.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("You have to specify a type.");
        }

        if (plant.getLightReq() == null || plant.getLightReq().trim().isEmpty()) {
            throw new IllegalArgumentException("You have to input a light requirement.");
        }

        if (plant.getWaterReq() == null || plant.getWaterReq().trim().isEmpty()) {
            throw new IllegalArgumentException("You have to input a water requirement.");
        }

        if (plant.getImages() == null || plant.getImages().isEmpty()) {
            throw new IllegalArgumentException("You need to attach an image");
        }

        if (plant.getAge() < 0 || plant.getAge() > 100) {
            throw new IllegalArgumentException("The age can not be less than 0 or more than 100.");
        }

        if (plant.getDifficulty() < 1 || plant.getDifficulty() > 5) {
            throw new IllegalArgumentException("Difficulty must be between 1 and 5.");
        }
    }
}