package com.stykkapi.stykka.controllers;


import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/register")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "/buyer")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterBuyerDTO newBuyer){
        try {
            buyerService.registerBuyer(newBuyer);
        } catch (EmailExistsException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(newBuyer.toString() + "\n\nSuccessfully Registered", HttpStatus.ACCEPTED);
    }
}
