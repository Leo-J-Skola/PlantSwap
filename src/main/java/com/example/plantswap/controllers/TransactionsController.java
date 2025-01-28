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

    @PostMapping("/trade")
    public ResponseEntity<Transactions> tradeTransaction(@RequestBody Transactions transaction) {
        Transactions createdTradeTransaction = transactionServices.createTradeTransaction(transaction);
        return new ResponseEntity<>(createdTradeTransaction, HttpStatus.CREATED);
    }

    

    @PostMapping("/sell/{userId}/{plantId}/{price}")
    public ResponseEntity<Transactions> sellTransaction(@RequestBody Transactions transaction, @PathVariable String userId, @PathVariable String plantId, @PathVariable int price) {
        Transactions createdSellTransaction = transactionServices.createSellTransaction(transaction, userId, plantId, price, null, null);
        return new ResponseEntity<>(createdSellTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<Transactions>> getTransactionsByUserId(@PathVariable String userId) {
        Optional<Transactions> transaction = transactionServices.getTransactionsByUserId(userId);
        return new ResponseEntity<>(transaction, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transaction = transactionServices.getAllTransactions();
        return new ResponseEntity<>(transaction, HttpStatus.FOUND);
    }

}
