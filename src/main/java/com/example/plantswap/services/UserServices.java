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

import java.util.*;

@Service
public class UserServices {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private PlantsRepo plantsRepo;
    @Autowired
    private TransactionsRepo transactionsRepo;

    public Users createUser(Users user) {
        validateUser(user);
        return usersRepo.save(user);
    }

    public void deleteUser(Users user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User not found.");
        }
        usersRepo.delete(user);
    }

    public Users updateUser(String id, Users user) {
        validateUser(user);                                             //Gets the user id from the url (http://localhost:8080/users/{id})
        Optional<Users> existingUser = usersRepo.findById(id);          //so you dont have to add "id:" "" with the correct id
        if (existingUser.isEmpty()) {                                   //makes it so you cant accidentally create a new user
            throw new IllegalArgumentException("User not found.");
        }
        user.setId(existingUser.get().getId());
        user.setName(user.getName());
        return usersRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    public List<Users> getUserByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name can't be empty or null.");
        }
        List<Users> users = usersRepo.findByName(name);
        if (users.isEmpty()) {
            throw new NoSuchElementException("No user found with name '" + name + "'.");
        }
        return users;
    }

    public List<Plants> getUserPlants(String userId) {
        return plantsRepo.findByUserId(userId);
    }

    //This makes sure a user cannot update or create a user with invalid information.
    private void validateUser(Users user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name. It cannot be empty");
        }
        if (user.getTransactionId() == null || user.getTransactionId().isEmpty()) {
            user.setTransactionId(new ArrayList<>());
        }
        if (user.getPlantId() == null || user.getPlantId().isEmpty()) {
            user.setPlantId(new ArrayList<>());
        }
    }
}



