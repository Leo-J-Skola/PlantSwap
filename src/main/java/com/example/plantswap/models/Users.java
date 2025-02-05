package com.example.plantswap.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Users {
    @Id
    private String id;
    private String userId;
    private String name;
    private List<String> transactionId; //all transactions the user has posted
    private List<String> plantId; //the plants the user owns

    public Users(String id, String name, List<String> plantId, List<String> transactionId) {
        this.id = id;
        this.name = name;
        this.plantId = plantId;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlantId() {
        return plantId;
    }

    public void setPlantId(List<String> plantId) {
        this.plantId = plantId;
    }

    public List<String> getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(List<String> transactionId) {
        this.transactionId = transactionId;
    }

}
