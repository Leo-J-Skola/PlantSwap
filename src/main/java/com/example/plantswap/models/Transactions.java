package com.example.plantswap.models;

import jakarta.validation.constraints.Max;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "transactions")
public class Transactions {
    @Max(value=10, message = "The price can not be more than 10.")
    @Id
    private String id;
    private int price;

    private String transactionType;

    private String userId;
    private boolean available;
    private String tradeOffers; //This is a trade offer id, since we want users to be able to make multiple trade offers
    private String plantId;
    private String transactionId;
    private ObjectId buyerId;

    private String trade_offer;
    private String trade_status; //accept,decline or pending (pending meaning a trade offer has been made and is waiting to be accepted or declined)

/*    public SellTransactions(String transactionId, String userId, String plantId) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.plantId = plantId;
    }*/

    public Transactions(String id, String userId, String plantId, int price) {
        this.id = id;
        this.userId = userId;
        this.plantId = plantId;
        this.price = price;
    }

    /*    public Transactions(Users userId, Plants plantId, int price, String trade_offer, String trade_status) {
            this.trade_offer = trade_offer;
            this.trade_status = trade_status;
            this.price = price;
            this.buyerId = userId.getId();
            this.trade_offer = plantId.getId();

        }*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
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

    public String getTradeOffers() {
        return tradeOffers;
    }

    public void setTradeOffers(String tradeOffers) {
        this.tradeOffers = tradeOffers;
    }
    public String getTransactionType(String transactionType) {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public String getTrade_offer(String trade_offer) { //Again, this is the plant id of the buyer id user
        return trade_offer;
    }

    public void setTrade_offer(String trade_offer) {
        this.trade_offer = trade_offer;
    }

    public String getTrade_status(String trade_status) {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }
}
