package com.stykkapi.stykka.controllers;

import com.stykkapi.stykka.dtos.LoginDTO;
import com.stykkapi.stykka.exceptions.InvalidEmailException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class BuyerLoginController {

    @Autowired
    private BuyerService buyerService;


    @GetMapping(value = "/buyer")
    public void buyerLogin(@RequestBody LoginDTO existingBuyer){
        try {
            buyerService.loginBuyer(existingBuyer);
        } catch (InvalidPasswordException | InvalidEmailException e) {
            e.getLocalizedMessage();
        }
    }
}
