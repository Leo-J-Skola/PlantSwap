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

    @Autowired
    private UserServices userServices;

    @Autowired
    private TransactionServices transactionServices;

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

    public Plants updatePlant(Plants plant) {
        validatePlant(plant);
        updateExistingPlant(plant);
        return plantsRepo.save(plant);
    }

    //Currently this forces me to update ALL of these at once.
    //I need to fix this later if i want to, for example just change the difficulty.

    private void updateExistingPlant(Plants plant) {
        plant.setName(plant.getName());

        plant.setDifficulty(plant.getDifficulty());
        plant.setScientificName(plant.getScientificName());
        plant.setType(plant.getType());
        plant.setLightReq(plant.getLightReq());
        plant.setWaterReq(plant.getWaterReq());
        plant.setImages(plant.getImages());
        plant.setAge(plant.getAge());
    }


    public List<Plants> getAllPlants() {
        return plantsRepo.findAll();
    }

    public Optional<Plants> getPlantById(ObjectId id) {
        if (id == null) {
            throw new IllegalArgumentException("Plant id can not be empty or null.");
        }
        Optional<Plants> plants = plantsRepo.findById(id);
        if (plants.isEmpty()) {
            throw new NoSuchElementException("No plant found with id '" + id + "'.");
        }
        return plants;
    }

    //This makes sure a user cannot update or create a plant with invalid information.

    private void validatePlant(Plants plant) {
        if (plant.getName() == null || plant.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Plant name can not be empty or null.");
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
