package com.example.plantswap.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

@Document(collection = "users")
public class Users {
    @Id
    private String id;
    private String name;
    private List<String> transactionIds;
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
    public List<String> getTransactionIds() {
        return transactionIds;
    }
    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

}
