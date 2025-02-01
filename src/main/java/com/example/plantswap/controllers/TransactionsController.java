package com.example.plantswap.controllers;

import com.example.plantswap.models.Transactions;

import com.example.plantswap.models.Users;
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

    @PostMapping("/trade")
    public ResponseEntity<Transactions> tradeTransaction(@RequestBody Transactions transaction) {
        Transactions createdTradeTransaction = transactionServices.createTradeTransaction(transaction);
        return new ResponseEntity<>(createdTradeTransaction, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Optional<Transactions>> getTransactionsByUserId(@PathVariable String userId) {
        Optional<Transactions> transaction = transactionServices.getTransactionsByUserId(userId);
        return new ResponseEntity<>(transaction, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> user = transactionServices.getAllTransactions();
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

        @PostMapping("/trade/{userId}/{plantId}")
        public ResponseEntity<Transactions> createTradeTransaction(String transactionId, @PathVariable String userId, @PathVariable String plantId) {
            Transactions transaction = new Transactions(transactionId, userId, plantId);
            transactionsRepo.save(transaction);
            return ResponseEntity.ok(transaction);
        }

        @GetMapping("/trades")
        public ResponseEntity<Optional<Transactions>> getTradeTransactions() {
            Optional<Transactions> trades = transactionsRepo.findByTransactionType("trade");
            if (!trades.isEmpty()) {
                return ResponseEntity.ok(trades);
            } else {
                return ResponseEntity.notFound().build();
        }
        }


    }


