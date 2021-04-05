package com.stykkapi.stykka.controllers;


import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping( "/register")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterBuyerDTO newBuyer){
        try {
            buyerService.registerBuyer(newBuyer);
        } catch (EmailExistsException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(newBuyer.toString() + "\n\nSuccessfully Registered", HttpStatus.CREATED);
    }

    @GetMapping( "/all")
    public List<Buyer> getAll() {
        return buyerService.getAllBuyers();
    }

    @GetMapping("/{id}")
    public Buyer getBuyerById(@PathVariable String id){

        Optional<Buyer> optionalBuyer = buyerService.getOneBuyer(id);
        return optionalBuyer.orElse(null);
    }
}
