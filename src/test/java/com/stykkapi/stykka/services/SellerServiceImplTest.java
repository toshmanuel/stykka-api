package com.stykkapi.stykka.services;

import com.stykkapi.stykka.models.Seller;
import com.stykkapi.stykka.repositories.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SellerServiceImplTest {

    SellerRepository sellerRepository;

    Seller seller;

    @BeforeEach
    void setUp() {
        seller = new Seller();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createSeller(){
        seller.setSellerFirstName("Seller");
        seller.setSellerLastName("");
        seller.setSellerEmail("");
        seller.setSellerPassword("");
        seller.setStoreName("");
        seller.setBankName("");
        seller.setAccountNumber("");

        sellerRepository.save(seller);

        assertEquals("seller", seller.getSellerFirstName());
    }
}