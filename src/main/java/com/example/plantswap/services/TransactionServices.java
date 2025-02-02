package com.example.plantswap.services;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import com.example.plantswap.repo.PlantsRepo;
import com.example.plantswap.repo.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.TransactionSuspensionNotSupportedException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServices {

    @Autowired
    private TransactionsRepo transactionsRepo;
    @Autowired
    private PlantsRepo plantsRepo;

/*    public Transactions createTransaction(Transactions transaction) {
        *//*        validateTransaction(transaction);*//*
        return transaction;
    }*/

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.findAll();
    }


    public Optional<Transactions> getTransactionById(String id) {
        return transactionsRepo.findById(id);
    }

    public Optional<Transactions> getTransactionsByUserId(String id) {
        return transactionsRepo.findByUserId(id);
    }

    public Optional<Transactions> getTradeTransactions(String id) {
        return transactionsRepo.findByTransactionType("trade");
    }

    public Optional<Transactions> getSellTransactions(String id) {
        return transactionsRepo.findByTransactionType("sell");
    }

    public Transactions createTransaction(Transactions transaction) {
        if (transaction.getUserId() == null) {
            throw new IllegalArgumentException("Could not find user with that id.");
        }
        if (transaction.getPlantId() == null) {
            throw new IllegalArgumentException("Could not find plant with that id.");
        }
        if (Objects.equals(transaction.getTransactionType(), "sell")) {
            if (transaction.getPrice() > 50 || transaction.getPrice() < 1000) {
                throw new IllegalArgumentException("Plant price must be between 50 and 1000.");
            }
            transaction.setTransactionType("sell");
            transaction.setAvailable(true);
            transactionsRepo.save(transaction);
            return transaction;
        }
        transaction.setTransactionType("trade");
        transaction.setAvailable(true);
        transaction.setTrade_status("");
        transactionsRepo.save(transaction);
        return transaction;
    }

}