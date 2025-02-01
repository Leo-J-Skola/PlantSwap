package com.example.plantswap.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Users {
    @Id
    private String id;
    private String name;
    private List<String> transactionId;
    private List<Users> plantId; //the plants the user owns

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
    public List<Users> getPlantId() {
        return plantId;
    }
    public void setPlantId(List<Users> plantId) {
        this.plantId = plantId;
    }
    public List<String> getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(List<String> transactionId) {
        this.transactionId = transactionId;
    }

}
