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

    public Optional<Transactions> getTransactionById(String id) {
        return transactionsRepo.findById(id);
    }

    public List<Users> getTransactionsByUserId(String id) {
        return usersRepo.findByTransactionId(id);
    }

    //Create a transaction by checking the transaction type (trade or sell) and that a user has a plant
    //Also had to make a check for max allowed transactions per user here

    public Transactions createTransaction(String userId, String plantId, Transactions transaction) {
        long activeTransactions = activeTransactions(userId);
        if (activeTransactions >= 10) {
            throw new IllegalStateException("A user cannot have more than 10 active transactions at once.");
        }
        Plants plant = plantsRepo.findById(plantId).orElseThrow(() -> new IllegalArgumentException("Plant not found."));
        Users user = usersRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!plant.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("User does not own this plant.");
        }
        if (plant.getUserId() == null) {
            throw new IllegalStateException("Plant does not have an owner.");
        }

        validateCreateTransaction(transaction);
        transaction.setUserId(user.getId());
        transaction.setPlantId(plant.getId());
        transaction.setAvailable(true);

        return transactionsRepo.save(transaction);
    }

        //This checks for the maximum amount of transactions a user can have
        //Its more explained in TransactionsRepo.class

        public long activeTransactions(String userId) {
        return transactionsRepo.countByUserIdAndAvailable(userId, true);
    }

            //Gets the transaction id from the url (http://localhost:8080/transactions/{id})
            //so you dont have to add "id:" "" with the correct id
            //makes it so you cant accidentally create a new transaction

    public Transactions updateTransaction(String id, Transactions transaction) {
        Transactions existingTransaction = transactionsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));

        if (!transaction.getTransactionType().equals("trade") && !transaction.getTransactionType().equals("sell")) {
            throw new IllegalArgumentException("You have to specify if you want to trade or sell your plant.");
        }
            if (transaction.getTransactionType().equals("sell")) {
            if (transaction.getPrice() < 50 || transaction.getPrice() > 1000) {
                throw new IllegalArgumentException("Price must be between 50 and 1000 for a plant.");
            }
                existingTransaction.setPrice(transaction.getPrice());

            } else if (transaction.getTransactionType().equals("trade")) {
                if (transaction.getPlantId() == null) {
                    throw new IllegalArgumentException("You have to input a plant id");
                }
                existingTransaction.setPlantId(transaction.getPlantId());
                existingTransaction.setPrice(null);
            }
                existingTransaction.setTransactionType(transaction.getTransactionType());
                return transactionsRepo.save(existingTransaction);
    }

        //Updates an existing transaction, adding trade status and trade offer from a user with his plant
        //addTradeOffer and updateTradeStatus methods are both related to making trading work

    public Transactions addTradeOffer(String transactionId, String plantId, String userId) {
        Transactions addTradeOffer = transactionsRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));
        Plants plant = plantsRepo.findById(plantId).orElseThrow(() -> new IllegalArgumentException("Plant not found."));
        Users user = usersRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!plant.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("User does not own this plant.");
        }
        addTradeOffer.setTradeOfferId(plantId);
        addTradeOffer.setTradeStatus("pending");

        return transactionsRepo.save(addTradeOffer);
    }

            //This checks that the user owns the transaction before being able to accept or decline a trade

    public Transactions updateTradeStatus(String transactionId, String userId, String status) {
        Transactions transaction = transactionsRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));

        if (!transaction.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User is not the owner of this transaction.");
        }

        if (!status.equals("accept") && !status.equals("decline") && !status.equals("completed")) {
            throw new IllegalArgumentException("Invalid status. Must be accept, decline.");
        }
        transaction.setTradeStatus(status);

        if (status.equals("accept")) {
            //This swaps plants between users, also checks if the plants exist
            Plants offeredPlant = plantsRepo.findById(transaction.getTradeOfferId()).orElseThrow(() -> new IllegalArgumentException("Offered plant not found."));
            Plants originalPlant = plantsRepo.findById(transaction.getPlantId()).orElseThrow(() -> new IllegalArgumentException("Original plant not found."));

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

        //Same checks as trading, but instead also checks if its a transaction for sale and not trading
        //then makes sure the price input is the same as the price on the transaction
        //also gotta make sure the buyer and seller owns their plants

    public Transactions buyTransaction(String transactionId, String buyerUserId, int buyerPrice) {
        Transactions transaction = transactionsRepo.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction not found."));
        if (!transaction.getTransactionType().equals("sell")) {
            throw new IllegalArgumentException("This transaction is for trading, not for sale.");
        }
        if (buyerPrice != transaction.getPrice()) {
            throw new IllegalArgumentException("Incorrect price.");
        }
        Users buyer = usersRepo.findById(buyerUserId).orElseThrow(() -> new IllegalArgumentException("Buyer not found."));
        Users seller = usersRepo.findById(transaction.getUserId()).orElseThrow(() -> new IllegalArgumentException("Seller not found."));
        Plants plant = plantsRepo.findById(transaction.getPlantId()).orElseThrow(() -> new IllegalArgumentException("Plant not found."));

        plant.setUserId(buyer.getId());
        buyer.setPlantId(plant.getId());
        seller.setPlantId(plant.getId());

        plantsRepo.save(plant);
        usersRepo.save(buyer);
        usersRepo.save(seller);

        transaction.setAvailable(false);
        transaction.setTradeStatus("Completed. Removing transaction from listings...");

        transactionsRepo.delete(transaction);
        return transaction;
    }

    //This is postman body check so you have to specify its a trade or if you are selling the plant

    public void validateCreateTransaction(Transactions transaction) {
        if (!transaction.getTransactionType().equals("trade") && !transaction.getTransactionType().equals("sell")) {
            throw new IllegalArgumentException("You have to input if you want to trade or sell your plant.");
        }
        if (transaction.getTransactionType().equals("sell") && (transaction.getPrice() < 50 || transaction.getPrice() > 1000)) {
            throw new IllegalArgumentException("Price must be between 50 and 1000 for a plant.");
        }
    }
}
