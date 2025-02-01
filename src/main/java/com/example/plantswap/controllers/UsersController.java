package com.example.plantswap.controllers;

import com.example.plantswap.models.Plants;
import com.example.plantswap.repo.UsersRepo;
import com.example.plantswap.models.Users;
import com.example.plantswap.repo.PlantsRepo;
import com.example.plantswap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Users> updateUser(@RequestBody Users id) {
        Users updatedUser = userServices.updateUser(id);
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
    public ResponseEntity<List<Users>> getAllPlants(@PathVariable String id) {
        List<Users> user = userServices.getAllPlants(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }


}




