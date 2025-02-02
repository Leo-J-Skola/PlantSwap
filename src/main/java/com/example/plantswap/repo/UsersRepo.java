package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import com.example.plantswap.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Optional;

public interface UsersRepo extends MongoRepository<Users, String> {

    List<Users> findByName(String name);
    Optional<Users> findById(String id);
    List<Users> findByPlantId(String plantId);
}