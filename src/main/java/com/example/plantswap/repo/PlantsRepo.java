package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
public interface PlantsRepo extends MongoRepository<Plants, ObjectId> {
    Optional<Plants> findById(ObjectId id);
    List<Plants> findByUserId(ObjectId userId);
}
