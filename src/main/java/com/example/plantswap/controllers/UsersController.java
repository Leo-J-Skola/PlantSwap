package com.example.plantswap.controllers;

import com.example.plantswap.models.Plants;
import com.example.plantswap.models.Transactions;
import com.example.plantswap.models.Users;
import com.example.plantswap.services.PlantServices;
import com.example.plantswap.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userServices.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Users id) {
        userServices.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable ObjectId id, @RequestBody Users user) {
        Users updatedUser = userServices.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> getAllUsers = userServices.getAllUsers();
        return new ResponseEntity<>(getAllUsers, HttpStatus.FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Users>> getUserByName(@PathVariable String name) {
        List<Users> getUserByName = userServices.getUserByName(name);
        return new ResponseEntity<>(getUserByName, HttpStatus.FOUND);
    }

    @GetMapping("/{id}/plants")
    public ResponseEntity<List<Plants>> getUserPlants(@PathVariable ObjectId id) {
        List<Plants> getUserPlants = userServices.getUserPlants(id);
        return new ResponseEntity<>(getUserPlants, HttpStatus.FOUND);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transactions>> getUserTransactions(@PathVariable ObjectId id) {
        List<Transactions> getUserTransactions = userServices.getUserTransactions(id);
        return new ResponseEntity<>(getUserTransactions, HttpStatus.FOUND);
    }
}


