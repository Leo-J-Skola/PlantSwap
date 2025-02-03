package com.example.plantswap.models;

import jakarta.validation.constraints.Max;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "transactions")
public class Transactions {
    @Max(value=10, message = "The price can not be more than 10.")
    @Id
    private String id;
    private String transactionId;
    private String userId;
    private String plantId;
    private String transactionType; //This is the type of transaction, trade or sell
    private Integer price;
    private Boolean available;
    private List<String> tradeOfferId; // Users offering trade
    private String tradeStatus; //accept,decline or pending (pending meaning a trade offer has been made and is waiting to be accepted or declined)

/*    public SellTransactions(String transactionId, String userId, String plantId) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.plantId = plantId;
    }*/

    public Transactions(String id, String userId, String plantId, String transactionType, Integer price) {
        this.id = id;
        this.userId = userId;
        this.plantId = plantId;
        this.transactionType = transactionType;
        this.price = price;
        this.available = true;
        this.tradeOfferId = List.of();
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

    public List<String> getTradeOfferId() {
        return tradeOfferId;
    }
    public void setTradeOfferId(List<String> tradeOfferId) {
        this.tradeOfferId = tradeOfferId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
