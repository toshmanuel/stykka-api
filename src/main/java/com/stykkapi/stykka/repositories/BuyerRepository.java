package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.models.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
}
