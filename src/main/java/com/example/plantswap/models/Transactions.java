package com.example.plantswap.models;

import jakarta.validation.constraints.Max;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Document(collection = "transactions")
public class Transactions {
    @Id
    private String id;
    private String transactionId;
    private String userId;
    private String plantId;
    private String tradeOfferId; // Users offering trade

    private String transactionType; //This is the type of transaction, trade or sell
    private Integer price;
    private Boolean available;
    private String tradeStatus; //accept,decline or pending (pending meaning a trade offer has been made and is waiting to be accepted or declined)

    public Transactions(String id, String userId, String plantId, String transactionType, Integer price) {
        this.id = id;
        this.userId = userId;
        this.plantId = plantId;
        this.transactionType = transactionType;
        this.price = price;
        this.available = true;
        this.tradeStatus = "pending";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getTransactionType(String transactionType) {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTradeOfferId() {
        return tradeOfferId;
    }

    public void setTradeOfferId(String tradeOfferId) {
        this.tradeOfferId = tradeOfferId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
