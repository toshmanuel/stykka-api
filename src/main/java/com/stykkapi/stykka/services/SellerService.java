package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterSellerDTO;
import com.stykkapi.stykka.exceptions.SellerException;
import com.stykkapi.stykka.models.Seller;

import java.util.List;
import java.util.Optional;


public interface SellerService {

    List<Seller> findAllSeller();

    Optional<Seller> findBySellerByName(String sellerId);

    void deleteBySellerId(String sellerId) throws SellerException;


    void saveSeller(RegisterSellerDTO seller) throws SellerException;
}
