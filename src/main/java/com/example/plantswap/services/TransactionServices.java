package com.example.plantswap.services;

import com.example.plantswap.models.Transactions;
import com.example.plantswap.repo.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {

    @Autowired
    private TransactionsRepo transactionsRepo;

    public Transactions createTransaction(Transactions transaction) {
/*        validateTransaction(transaction);*/
        return transaction;
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.findAll();
    }

    public Transactions getTransactionById(String id) {
        return transactionsRepo.findById(id).get();
    }

    public Optional<Transactions> getTransactionsByUserId(String id) {
        return transactionsRepo.findByUserId(id);
    }


    public Transactions createSellTransaction(Transactions transaction, String userId, String plantId, int price, Object o, Object o1) {
        if (transaction.getId() == null) {
            throw new IllegalArgumentException("The user_id can not be empty or null.");
        }

        if (transaction.getPrice() < 50 || transaction.getPrice() > 1000) {
            throw new IllegalArgumentException("Plant price can not be less than 50 or more than 1000.");
        }

        transactionsRepo.save(transaction);
        return transaction;
    }

    public Transactions createTradeTransaction(Transactions transaction ) {
        if (transaction.getId() == null) {
            throw new IllegalArgumentException("The plant id can not be empty or null.");

        }
        return transaction;
    }

/*    private void validateTransaction(Transactions transaction) {
        if (transaction.getTrade() != "trade" || transaction.getSell() != "sell")  {
            throw new IllegalArgumentException("You have to specify if you want to trade or sell your plant.");
        }

        }*/
}

