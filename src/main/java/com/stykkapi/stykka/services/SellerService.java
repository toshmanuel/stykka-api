package com.stykkapi.stykka.services;

import com.stykkapi.stykka.models.Seller;

import java.util.List;
import java.util.Optional;


public interface SellerService {

    List<Seller> findAllSeller();

    Optional<Seller> findBySellerId(String sellerId);

    Optional<Seller> deleteSellerById(String sellerId);


    void saveSeller(Seller seller);
}
