package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepo extends MongoRepository<Transactions, ObjectId> {

    Optional<Transactions> findById(ObjectId id);
    List<Transactions> findByUserId(ObjectId userId);
    Optional<Transactions> findByPlantId(ObjectId plantId);
    Optional<Transactions> findByTransactionType(String transactionType);
    Optional<Transactions> findByAvailable(boolean available);
    List<Transactions> findByTransactionId(ObjectId transactionId);

}