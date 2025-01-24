package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantsRepo extends MongoRepository<Plants, String> {
}
