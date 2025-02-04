package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepo extends MongoRepository<Transactions, ObjectId> {
    long countByUserIdAndAvailable(String userId, boolean available);              //My friend told me use @Max(value =..) but it doesnt work so
                                                                                   //im making a method to cap the amount of transactions a user can have instead
    Optional<Transactions> findById(ObjectId id);                                  //countBy is spring data like findBy... I want to count the number of transactions a user has
    List<Transactions> findByUserId(ObjectId userId);                              //so userId and available is what i check. So more than 10 available transactions will not be allowed.
    Optional<Transactions> findByPlantId(ObjectId plantId);                        //I also use "long" because it stores a lot more numbers since we are dealing with ids and arrays
    Optional<Transactions> findByTransactionType(String transactionType);
    Optional<Transactions> findByAvailable(boolean available);
    List<Transactions> findByTransactionId(ObjectId transactionId);
    List<Transactions> findByTradeOfferId(ObjectId tradeOfferId);
}