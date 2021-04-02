package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String> {
}
