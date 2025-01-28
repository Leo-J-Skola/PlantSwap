package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepo extends MongoRepository<Transactions, String> {

    Optional<Transactions> findById(String id);
    Optional<Transactions> findByUserId(String userId);

}
