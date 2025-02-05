package com.example.plantswap.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Document(collection = "plants")
public class Plants {
    @Id
    private String id;
    private String userId; //Person that owns the plant
    private String plantId;
    private String transactionId;

    private String name;
    private String scientificName;
    private double age;
    private String type;
    private String lightReq;
    private String waterReq;
    private int difficulty;
    private int price;
    private List<String> images;

    public Plants(String id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLightReq() {
        return lightReq;
    }

    public void setLightReq(String lightReq) {
        this.lightReq = lightReq;
    }

    public String getWaterReq() {
        return waterReq;
    }

    public void setWaterReq(String waterReq) {
        this.waterReq = waterReq;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}