package com.example.plantswap.controllers;

import com.example.plantswap.models.Transactions;

import com.example.plantswap.repo.TransactionsRepo;
import com.example.plantswap.services.TransactionServices;
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
    public ResponseEntity<List<Transactions>> getTransactionsByUserId(@PathVariable String userId) {
        List<Transactions> getTransactionsByUserId = transactionServices.getTransactionsByUserId(userId);
        return new ResponseEntity<>(getTransactionsByUserId, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transactions>> getTransactionById(@PathVariable String id) {
        Optional<Transactions> getTransactionById = transactionServices.getTransactionById(id);
        return new ResponseEntity<>(getTransactionById, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionServices.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.FOUND);
    }

    @PostMapping("/{userId}/{plantId}")
    public ResponseEntity<?> createTransaction(@PathVariable String userId, @PathVariable String plantId, @RequestBody Transactions transaction) {
            Transactions newTransaction = transactionServices.createTransaction(userId, plantId, transaction);
            return ResponseEntity.ok(newTransaction);
        }

    @PutMapping ("/{id}")
    public ResponseEntity<Transactions> updateUser(@PathVariable String id, @RequestBody Transactions transactions) {
        Transactions updatedTransaction = transactionServices.updateTransaction(id, transactions);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{transactionId}/{plantId}/{userId}")
    public ResponseEntity<?> addTradeOffer(@PathVariable String transactionId, @PathVariable String plantId, @PathVariable String userId) {
        Transactions updatedTransaction = transactionServices.addTradeOffer(transactionId, plantId, userId);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PutMapping("/{transactionId}/{userId}/status")
    public ResponseEntity<?> updateTradeStatus(@PathVariable String transactionId, @PathVariable String userId, @RequestParam String status) {
        Transactions updatedTransaction = transactionServices.updateTradeStatus(transactionId, userId, status);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PutMapping("/buy/{transactionId}/{buyerUserId}")
    public ResponseEntity<?> buyTransaction(@PathVariable String transactionId, @PathVariable String buyerUserId, @RequestParam int buyerPrice) {
        Transactions updatedTransaction = transactionServices.buyTransaction(transactionId, buyerUserId, buyerPrice);
        return ResponseEntity.ok(updatedTransaction);
    }
}