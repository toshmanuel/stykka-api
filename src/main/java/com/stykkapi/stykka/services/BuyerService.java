package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.AddressDTO;
import com.stykkapi.stykka.dtos.ChangePasswordDTO;
import com.stykkapi.stykka.dtos.LoginDTO;
import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.exceptions.InvalidEmailException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.models.Buyer;

import java.util.List;
import java.util.Optional;

public interface BuyerService{
    Buyer registerBuyer(RegisterBuyerDTO newBuyer) throws EmailExistsException;

    List<Buyer> getAllBuyers();

    Optional<Buyer> getOneBuyer(String buyerId);

    Buyer updateBuyer(Buyer buyerToUpdate, String buyerId);

    Buyer changePassword(ChangePasswordDTO buyerPasswordDTO, String buyerId) throws InvalidPasswordException;

    Optional<Buyer> deleteBuyer(String buyerId);

    String loginBuyer(LoginDTO buyer) throws InvalidPasswordException, InvalidEmailException;

    Buyer addAddress(AddressDTO addressDTO, String buyerId);
}
