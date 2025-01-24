package com.example.plantswap.repo;

import com.example.plantswap.models.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantRepo extends MongoRepository<Plant, String> {
}
