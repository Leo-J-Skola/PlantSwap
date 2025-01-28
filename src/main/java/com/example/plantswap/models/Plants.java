package com.example.plantswap.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "plants")
public class Plants {
    @Id
    private ObjectId id;
    private String name;
    private String scientificName;
    private double age;
    private String type;
    private String lightReq;
    private String waterReq;
    private int difficulty;
    private String tradeOrSell;
    private int price;
    private List<String> images;
    private String status; //Checks if the plant is available
    private ObjectId ownerId; //Person that owns the plant

    public Plants() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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