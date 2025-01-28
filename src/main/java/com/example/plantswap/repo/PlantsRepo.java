package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Optional;

public interface PlantsRepo extends MongoRepository<Plants, String> {

    Optional<Plants> findById(String id);

    /*    List<Plants> findByScientificName(String scientificName);*/



/*    List<Plants> findByType(String type);

    List<Plants> findByTradeOrSell(String tradeOrSell);
    */


}
