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
        Transactions addTradeOffer = transactionsRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));
        Plants plant = plantsRepo.findById(plantId).orElseThrow(() -> new IllegalArgumentException("Plant not found."));
        Users user = usersRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!plant.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("User does not own this plant.");
        }
        addTradeOffer.getTradeOfferId().add("user " + userId + " is offering plant '" + plantId + "'.");
        addTradeOffer.setTradeStatus("pending");

        return transactionsRepo.save(addTradeOffer);
    }

            //This checks that the user owns the transaction before being able to accept or decline a trade

    public Transactions updateTradeStatus(ObjectId transactionId, ObjectId userId, String status) {
        Transactions transaction = transactionsRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));

        if (!transaction.getUserId().equals(userId.toString())) {
            throw new IllegalArgumentException("User is not the owner of this transaction.");
        }

        if (!status.equals("accept") && !status.equals("decline") && !status.equals("completed")) {
            throw new IllegalArgumentException("Invalid status. Must be accept, decline.");
        }
        transaction.setTradeStatus(status);

        if (status.equals("accept")) {
            //This swaps plants between users.Because postman doesnt like ObjectId, i am converting it to a string
            //Also checks if the plants exist

            Plants offeredPlant = plantsRepo.findById(new ObjectId(transaction.getTradeOfferId().get(0).split("'")[1])).orElseThrow(() -> new IllegalArgumentException("Offered plant not found."));
            Plants originalPlant = plantsRepo.findById(new ObjectId(transaction.getPlantId())).orElseThrow(() -> new IllegalArgumentException("Original plant not found."));

            String originalOwnerId = originalPlant.getUserId();
            originalPlant.setUserId(offeredPlant.getUserId());
            offeredPlant.setUserId(originalOwnerId);

            plantsRepo.save(originalPlant);
            plantsRepo.save(offeredPlant);

            transaction.setTradeStatus("Completed. Removing transaction from listings...");
            transactionsRepo.delete(transaction);
        }
        return transaction;
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