package com.example.plantswap.repo;

import com.example.plantswap.models.Plants;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface PlantsRepo extends MongoRepository<Plants, String> {

    Optional<Plants> findById(String id);
    List<Plants> findByName(String name);
    List<Plants> findByDifficulty(int difficulty);
    List<Plants> findByAge(double age);
    List<Plants> findByStatus(String status);
    List<Plants> findByPriceBetween(int minPrice, int maxPrice);

    /*    List<Plants> findByScientificName(String scientificName);*/



/*    List<Plants> findByType(String type);

    List<Plants> findByTradeOrSell(String tradeOrSell);
    */


}
