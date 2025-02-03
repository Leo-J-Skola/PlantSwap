package com.example.plantswap.controllers;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;

import com.example.plantswap.models.Users;
import com.example.plantswap.repo.TransactionsRepo;
import com.example.plantswap.services.TransactionServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionServices transactionServices;
    private TransactionsRepo transactionsRepo;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transactions>> getTransactionsByUserId(@PathVariable ObjectId userId) {
        List<Transactions> getTransactionsByUserId = transactionServices.getTransactionsByUserId(userId);
        return new ResponseEntity<>(getTransactionsByUserId, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transactions>> getTransactionById(@PathVariable ObjectId id) {
        Optional<Transactions> getTransactionById = transactionServices.getTransactionById(id);
        return new ResponseEntity<>(getTransactionById, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionServices.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.FOUND);
    }

/*    @GetMapping("/trades")
    public ResponseEntity<Optional<Transactions>> getTradeTransactions() {
        Optional<Transactions> trades = transactionServices.getTradeTransactions(("trade"));
        return ResponseEntity.ok(trades);
    }

    @GetMapping("/sells")
    public ResponseEntity<Optional<Transactions>> getSellTransactions() {
        Optional<Transactions> sells = transactionServices.getSellTransactions(("sell"));
        return ResponseEntity.ok(sells);
    }*/

    @PostMapping("/{userId}/{plantId}")
    public ResponseEntity<?> createTransaction(@PathVariable ObjectId userId, @PathVariable ObjectId plantId, @RequestBody Transactions transaction) {
            Transactions newTransaction = transactionServices.createTransaction(userId, plantId, transaction);
            return ResponseEntity.ok(newTransaction);
        }
    @PutMapping("/{transactionId}/{plantId}/{userId}")
    public ResponseEntity<?> addTradeOffer(@PathVariable ObjectId transactionId, @PathVariable ObjectId plantId, @PathVariable ObjectId userId) {
        Transactions updatedTransaction = transactionServices.addTradeOffer(transactionId, plantId, userId);
        return ResponseEntity.ok(updatedTransaction);
    }

}