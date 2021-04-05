package com.stykkapi.stykka.controllers;


import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterBuyerDTO newBuyer) {
        try {
            buyerService.registerBuyer(newBuyer);
        } catch (EmailExistsException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(newBuyer.toString() + "\n\nSuccessfully Registered", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/all")
    public List<Buyer> getAll() {
        return buyerService.getAllBuyers();
    }

    @GetMapping(value = "/{id}")
    public Buyer getBuyerById(@PathVariable String id) {

        Optional<Buyer> optionalBuyer = buyerService.getOneBuyer(id);
        return optionalBuyer.orElse(null);
    }

    @PatchMapping(value = "/{buyerId}")
    public Buyer updateBuyerInfo(@RequestBody Buyer buyerToUpdate, @PathVariable String buyerId) {
        return buyerService.updateBuyer(buyerToUpdate, buyerId);
    }

    @PutMapping(value = "/{buyerId}")
    public Object updateBuyerPassword(@RequestBody Buyer buyerToUpdate, @PathVariable String buyerId) {
        try {
            return buyerService.updateBuyerPassword(buyerToUpdate, buyerId);
        } catch (InvalidPasswordException e) {
            return e.getLocalizedMessage();
        }
    }

    @DeleteMapping(value = "/{buyerId}")
    public Optional<Buyer> deleteBuyer(@PathVariable String buyerId){
        return buyerService.deleteBuyer(buyerId);
    }
}
