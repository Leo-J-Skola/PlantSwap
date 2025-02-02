package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import com.example.plantswap.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
public interface UsersRepo extends MongoRepository<Users, ObjectId> {

    List<Users> findByName(String name);
    Optional<Users> findById(ObjectId id);
/*    Optional<Users> findByPlantId(ObjectId plantId);
    Optional<Users> findByTransactionId(ObjectId transactionId);
    Optional<Users> findPlantsByUserId(ObjectId id);*/
}