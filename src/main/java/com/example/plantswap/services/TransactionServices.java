package com.example.plantswap.services;

import com.example.plantswap.models.Transactions;
import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Users;
import com.example.plantswap.repo.TransactionsRepo;
import com.example.plantswap.repo.PlantsRepo;
import com.example.plantswap.repo.UsersRepo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionServices {

    @Autowired
    private TransactionsRepo transactionsRepo;

    @Autowired
    private PlantsRepo plantsRepo;

    @Autowired
    private UsersRepo usersRepo;

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.findAll();
    }

    public Optional<Transactions> getTransactionById(ObjectId id) {
        return transactionsRepo.findById(id);
    }

    public List<Transactions> getTransactionsByUserId(ObjectId id) {
        return transactionsRepo.findByUserId(id);
    }

    //Create a transaction by checking the transaction type (trade or sell) and that a user has a plant
    public Transactions createTransaction(ObjectId userId, ObjectId plantId, Transactions transaction) {
        Plants plant = plantsRepo.findById(plantId).orElseThrow(() -> new IllegalArgumentException("Plant not found."));
        Users user = usersRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!plant.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("User does not own this plant.");
        }
        if (plant.getUserId() == null) {
            throw new IllegalStateException("Plant does not have an owner.");
        }

        validateTransaction(transaction);
        transaction.setUserId(user.getId());
        transaction.setPlantId(plant.getId());
        transaction.setAvailable(true);

        return transactionsRepo.save(transaction);
    }
        //Updates an existing transaction, adding trade status and trade offer from a user with his plant
    public Transactions addTradeOffer(ObjectId transactionId, ObjectId plantId, ObjectId userId) {
        Transactions transaction = transactionsRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));
        Plants plant = plantsRepo.findById(plantId).orElseThrow(() -> new IllegalArgumentException("Plant not found."));
        Users user = usersRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!plant.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("User does not own this plant.");
        }
        transaction.getTradeOfferId().add("user " + userId + " is offering plant '" + plantId + "'.");
        transaction.setTradeStatus("pending");

        return transactionsRepo.save(transaction);
    }

    //This is postman body check so you have to specify its a trade or if you are selling the plant
    private void validateTransaction(Transactions transaction) {
        if (!transaction.getTransactionType().equals("trade") && !transaction.getTransactionType().equals("sell")) {
            throw new IllegalArgumentException("You have to input if you want to trade or sell your plant.");
        }
        if (transaction.getTransactionType().equals("sell") && (transaction.getPrice() < 50 || transaction.getPrice() > 1000)) {
            throw new IllegalArgumentException("Price must be between 50 and 1000 for a plant.");
        }
    }
}