package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.models.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
    Optional<Buyer> findByBuyerEmail(String buyerEmail);

    Optional<Buyer> findByBuyerId(String buyerId);

    Optional<Buyer> deleteBuyerByBuyerId(String buyerId);

    Optional<Buyer> findByBuyerPassword(String buyerPassword);

}
