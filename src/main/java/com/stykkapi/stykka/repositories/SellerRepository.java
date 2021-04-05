package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, String> {

    Optional<Seller> deleteBySellerId(String sellerId);

    Optional<Seller> findSellerEmail(String sellerEmail);
}
