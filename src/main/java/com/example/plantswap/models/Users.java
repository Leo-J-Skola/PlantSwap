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
    private String plantId; //the plants the user owns


    public Users(String id, String name, String plantId) {
        this.id = id;
        this.name = name;
        this.plantId = plantId;
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

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

}
