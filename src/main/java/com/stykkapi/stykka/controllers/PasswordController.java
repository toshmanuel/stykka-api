package com.stykkapi.stykka.controllers;


import com.stykkapi.stykka.dtos.ChangePasswordDTO;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/password")
public class PasswordController {
    @Autowired
    private BuyerService buyerService;


    @PostMapping(value ="/{buyerId}")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO buyerPasswordDTO,@PathVariable String buyerId){
        try {
            buyerService.changePassword(buyerPasswordDTO, buyerId);
            return new ResponseEntity<>("Password successfully changed", HttpStatus.ACCEPTED);
        } catch (InvalidPasswordException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
