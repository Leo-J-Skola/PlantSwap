package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlantsRepo extends MongoRepository<Plants, String> {
    Optional<Plants> findById(String id);
    List<Plants> findByUserId(String userId);
    List<Plants> findByPlantId(String plantId);
    List<Plants> findByName(String name);
    List<Plants> findByScientificName(String scientificName);
    List<Plants> findByType(String type);
    List<Plants> findByLightReq(String lightReq);
    List<Plants> findByWaterReq(String waterReq);
    List<Plants> findByImages(List<String> images);
    List<Plants> findByAge(double age);
    Optional<Plants> findByDifficulty(int difficulty);
}
