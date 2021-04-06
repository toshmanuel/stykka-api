package com.stykkapi.stykka.controllers;

import com.stykkapi.stykka.dtos.APIResponse;
import com.stykkapi.stykka.dtos.RegisterSellerDTO;
import com.stykkapi.stykka.exceptions.SellerException;
import com.stykkapi.stykka.services.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerSeller(@RequestBody RegisterSellerDTO registerSellerDTO) {
        try {
            sellerService.saveSeller(registerSellerDTO);
            return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
        } catch (SellerException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteSeller(@RequestBody String sellerId) {
        try {
            sellerService.deleteBySellerId(sellerId);
            return new ResponseEntity<>("Account deleted successfully", HttpStatus.NO_CONTENT);
        } catch (SellerException e) {
            log.debug("Error ", e);
            System.out.println("Test");
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
