package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.models.Buyer;

import java.util.List;
import java.util.Optional;

public interface BuyerService{
    Buyer registerBuyer(RegisterBuyerDTO newBuyer) throws EmailExistsException;

    List<Buyer> getAllBuyers();

    Optional<Buyer> getOneBuyer(String buyerId);

    Buyer updateBuyer(Buyer buyerToUpdate, String buyerId);

//    Buyer updateBuyerPassword(Buyer buyerToUpdate, String buyerId) throws InvalidPasswordException;

    Buyer addBuyerAddress(Buyer buyerToUpdate, String buyerId);

    Buyer updateBuyerAddress(Buyer buyerToUpdate, String buyerId);

    Optional<Buyer> deleteBuyer(String buyerId);
}
