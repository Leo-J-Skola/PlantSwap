package com.example.plantswap.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Optional;

@Document(collection = "transactions")
public class Transactions {
    @Id
    private String id;
    private int price;

    private String trade;
    private String sell;

    private String userId;

    private String plantId;
    private String buyerId;

    private String trade_offer; //This is the plantId being offered for the user that made the transaction listing
    private String trade_status; //accept,decline or pending (pending meaning a trade offer has been made and is waiting to be accepted or declined)

    /*    public Transactions(Users userId, Plants plantId, int price, String trade_offer, String trade_status) {
            this.trade_offer = trade_offer;
            this.trade_status = trade_status;
            this.price = price;
            this.buyerId = userId.getId();
            this.trade_offer = plantId.getId();

        }*/
    public Transactions() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

