package com.example.plantswap.controllers;

import com.example.plantswap.models.Plants;
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

/*    @PostMapping("/trade")
    public ResponseEntity<Transactions> tradeTransaction(@RequestBody Transactions transaction) {
        Transactions createdTradeTransaction = transactionServices.createTradeTransaction(transaction);
        return new ResponseEntity<>(createdTradeTransaction, HttpStatus.CREATED);
    }*/


    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<Transactions>> getTransactionsByUserId(@PathVariable String userId) {
        Optional<Transactions> getTransactionsByUserId = transactionServices.getTransactionsByUserId(userId);
        return new ResponseEntity<>(getTransactionsByUserId, HttpStatus.FOUND);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Optional<Transactions>> getTransactionById(@PathVariable String id) {
        Optional<Transactions> getTransactionById = transactionServices.getTransactionById(id);
        return new ResponseEntity<>(getTransactionById, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionServices.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.FOUND);
    }

    @PostMapping("/trade/{userId}/{plantId}")
    public ResponseEntity<Transactions> createTradeTransaction(@RequestBody Transactions transaction) {
        Transactions createTradeTransaction = transactionServices.createTransaction(transaction);
        transactionsRepo.save(createTradeTransaction);
        return ResponseEntity.ok(createTradeTransaction);
    }

    @GetMapping("/trades")
    public ResponseEntity<Optional<Transactions>> getTradeTransactions() {
        Optional<Transactions> trades = transactionServices.getTradeTransactions(("trade"));
        return ResponseEntity.ok(trades);
    }

    @GetMapping("/sells")
    public ResponseEntity<Optional<Transactions>> getSellTransactions() {
        Optional<Transactions> sells = transactionServices.getSellTransactions(("sell"));
        return ResponseEntity.ok(sells);
    }
    @PostMapping("/sell/{userId}/{plantId}/{price}")
    public ResponseEntity<Transactions> createSellTransaction(@RequestBody Transactions transaction) {
        Transactions createSellTransaction = transactionServices.createTransaction(transaction);
        return ResponseEntity.ok(createSellTransaction);
    }
}


