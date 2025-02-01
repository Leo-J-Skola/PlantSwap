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
        Optional<Transactions> getTransactionsByUserId = transactionServices.getTransactionsByUserId(userId);
        return new ResponseEntity<>(getTransactionsByUserId, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionServices.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.FOUND);
    }

        @PostMapping("/trade/{userId}/{plantId}")
        public ResponseEntity<Transactions> createTradeTransaction(String transactionId, @PathVariable String userId, @PathVariable String plantId) {
            Transactions createTradeTransaction = new Transactions(transactionId, userId, plantId);
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

/*        @PostMapping("/trade/{transactionId}/offer/{userId}/{tradeOffers}")
        public ResponseEntity<String> makeTradeOffer(@PathVariable String transactionId, @PathVariable String userId, @PathVariable String tradeOffers) {
            Optional<Transactions> existingTransaction = transactionsRepo.findById(transactionId);
            if (existingTransaction.isPresent()) {
                Transactions transaction = existingTransaction.get();
                transaction.addTradeOffer(userId, tradeOffers);
                transactionsRepo.save(transaction);
                return ResponseEntity.ok("You sent a trade offer.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to find transaction to trade.");
        }*/
    }


