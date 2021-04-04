package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private BuyerRepository buyerDb;

    @Override
    public Buyer registerBuyer(RegisterBuyerDTO newBuyer) throws EmailExistsException {
        Buyer buyer = new Buyer();
        Optional<Buyer> optionalBuyer = buyerDb.findByBuyerEmail(buyer.getBuyerEmail());
        if(optionalBuyer.isPresent()) throw new EmailExistsException("Email already exists");
        else{
            buyer.setBuyerFirstName(newBuyer.getFirstName());
            buyer.setBuyerLastName(newBuyer.getLastName());
            buyer.setBuyerEmail(newBuyer.getEmail());
            buyer.setBuyerPassword(newBuyer.getPassword());
            saveBuyerToDb(buyer);
        }
        return buyer;
    }

    private void saveBuyerToDb(Buyer buyer){
        buyerDb.save(buyer);
    }

    @Override
    public List<Buyer> getAllBuyers() {
        return buyerDb.findAll();
    }

    @Override
    public Optional<Buyer> getOneBuyer(String buyerId){
        if(buyerDb.findByBuyerId(buyerId).isPresent()){
            return buyerDb.findByBuyerId(buyerId);
        }else{
            throw new NoSuchElementException("Buyer with id: " + buyerId + " does not exist");
        }
    }

    @Override
    public Buyer updateBuyer(Buyer buyerToUpdate, String buyerId){
        Optional<Buyer> foundBuyer = buyerDb.findByBuyerId(buyerId);
        if(foundBuyer.isPresent()){
            foundBuyer.get().setBuyerFirstName(buyerToUpdate.getBuyerFirstName());
            foundBuyer.get().setBuyerLastName(buyerToUpdate.getBuyerLastName());
            foundBuyer.get().setBuyerEmail(buyerToUpdate.getBuyerEmail());
            saveBuyerToDb(foundBuyer.get());
            return foundBuyer.get();
        }else{
            throw new NoSuchElementException("Buyer with id: " + buyerId + " not found");
        }
    }

    @Override
    public Buyer updateBuyerPassword(Buyer buyerToUpdate, String buyerId) throws InvalidPasswordException, NoSuchElementException {
        Optional<Buyer> foundBuyer = Optional.ofNullable(buyerDb.findById(buyerId).orElseThrow(() -> new NoSuchElementException("Buyer with id: " + buyerId + " does not exist")));
        String oldPassword = foundBuyer.get().getBuyerPassword();
        foundBuyer.get().setBuyerPassword(buyerToUpdate.getBuyerPassword());

        if(oldPassword.equals(foundBuyer.get().getBuyerPassword())){
            foundBuyer.get().setNewPassword(buyerToUpdate.getNewPassword());
            foundBuyer.get().setBuyerPassword(buyerToUpdate.getNewPassword());
            saveBuyerToDb(foundBuyer.get());
            return foundBuyer.get();
        }else{
            throw new InvalidPasswordException("Sorry, passwords don't match");
        }
    }

    @Override
    public Buyer addBuyerAddress(Buyer buyerToUpdate, String buyerId) {

       return null;
    }

    @Override
    public Buyer updateBuyerAddress(Buyer buyerToUpdate, String buyerId) {
        return null;
    }

    @Override
    public Optional<Buyer> deleteBuyer(String buyerId){
        if(buyerDb.findByBuyerId(buyerId).isPresent()){
           return buyerDb.deleteBuyerByBuyerId(buyerId);
        }else{
            throw new NoSuchElementException("Buyer does not exist");
        }
    }


}
