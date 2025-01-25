package com.example.plantswap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plantswap.models.Plants;
import com.example.plantswap.repo.PlantsRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PlantServices {

    @Autowired
    private PlantsRepo plantsRepo;

    public Plants createPlant(Plants plant) {
        validatePlant(plant);

        return plantsRepo.save(plant);
    }

    public List<Plants> getAllPlants() {
        return plantsRepo.findAll();
    }

    public List<Plants> getPlantByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Plant name can not be empty or null.");
        }
        List<Plants> plants = plantsRepo.findByName(name);
        if (plants.isEmpty()) {
            throw new NoSuchElementException("No plant found with name '" + name + "'.");
        }
        return plants;
    }

    public List<Plants> getPlantByDifficulty(int difficulty) {
        if (difficulty < 1 || difficulty > 5) {
            throw new IllegalArgumentException("Difficulty must be between 1 and 5.");
        }
        return plantsRepo.findByDifficulty(difficulty);
    }

    public List<Plants> getPlantByAge(double age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be less than 0.");
        }
        return plantsRepo.findByAge(age);
    }

    public List<Plants> getPlantByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status can not be empty or null.");
        }
        return plantsRepo.findByStatus(status);
    }

    public List<Plants> getPlantByPriceRange(int minPrice, int maxPrice) {
        if (minPrice < 50 || maxPrice < 50 || maxPrice > 1000) {
            throw new IllegalArgumentException("Price cannot be less than 50 or more than 1000.");
        }
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Minimum price cannot be greater than maximum price.");
        }
        List<Plants> plants = plantsRepo.findByPriceBetween(minPrice, maxPrice);
        if (plants.isEmpty()) {
            throw new NoSuchElementException("No plant found with price between " + minPrice + " and " + maxPrice + ".");
    }
        return plantsRepo.findByPriceBetween(minPrice, maxPrice);
    }




        private void validatePlant(Plants plant) {
            if (plant.getName() == null || plant.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Plant name can not be empty or null.");
            }

            if (plant.getPrice() < 50 || plant.getPrice() > 1000) {
                throw new IllegalArgumentException("Plant price can not be less than 50 or more than 1000.");
            }

            if (plant.getScientificName() == null || plant.getScientificName().trim().isEmpty()) {
                throw new IllegalArgumentException("The scientific name can not be empty or null.");
            }

            if (plant.getType() == null || plant.getType().trim().isEmpty()) {
                throw new IllegalArgumentException("The type can not be empty or null.");
            }

            if (plant.getLightReq() == null || plant.getLightReq().trim().isEmpty()) {
                throw new IllegalArgumentException("The light requirement can not be empty or null.");
            }

            if (plant.getWaterReq() == null || plant.getWaterReq().trim().isEmpty()) {
                throw new IllegalArgumentException("The water requirement can not be empty or null.");
            }

            if (plant.getDifficulty() < 1 || plant.getDifficulty() > 5) { //Do i let the user put a custom difficulty?
                //Should i add a default difficulty depending on what plant it is? For now its custom.
                throw new IllegalArgumentException("Difficulty must be between 1 and 5.");
            }
        }
}




