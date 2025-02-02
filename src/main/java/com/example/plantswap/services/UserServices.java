package com.example.plantswap.services;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import com.example.plantswap.models.Users;
import com.example.plantswap.repo.TransactionsRepo;
import com.example.plantswap.repo.UsersRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plantswap.repo.PlantsRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserServices {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private PlantsRepo plantsRepo;
    @Autowired
    private TransactionsRepo transactionsRepo;

    public Users createUser(Users user) {
        /*        validateUser(user);*/
        return usersRepo.save(user);
    }

    public void deleteUser(Users user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        usersRepo.delete(user);
    }

    public Users updateUser(Users user) {
        /*        validateUser(user);*/
        updateExistingUser(user);
        return usersRepo.save(user);
    }

    private void updateExistingUser(Users user) {
        user.setName(user.getName());
        /*user.setPlantId(user.getPlantId());*/
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    public List<Users> getUserByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name can not be empty or null.");
        }
        List<Users> users = usersRepo.findByName(name);
        if (users.isEmpty()) {
            throw new NoSuchElementException("No user found with name '" + name + "'.");
        }
        return users;
    }

    public List<Plants> getUserPlants(ObjectId userId) {
        return plantsRepo.findByUserId(userId);
    }

    public List<Transactions> getUserTransactions(ObjectId userId) {
        return transactionsRepo.findByUserId(userId);

        //This makes sure a user cannot update or create a user with invalid information.
/*    private void validateUser(Users user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("name can not be empty or null.");

        }

        if (user.getPlantId() == null || user.getPlantId().isEmpty()) {
            user.setPlantId(null);
        }

        if (user.getTransactionId() == null || user.getTransactionId().isEmpty()) {
            user.setTransactionId(null);
        }
    }*/
    }
}
