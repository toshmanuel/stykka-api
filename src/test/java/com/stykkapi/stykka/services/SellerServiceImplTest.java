package com.stykkapi.stykka.services;

import com.stykkapi.stykka.models.Seller;
import com.stykkapi.stykka.repositories.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SellerServiceImplTest {
    @Autowired
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
        seller.setSellerLastName("last name");
        seller.setSellerEmail("selleremail@email.com");
        seller.setSellerPassword("pws");
        seller.setStoreName("my store");
        seller.setBankName("My Bank");
        seller.setAccountNumber("99002");

        sellerRepository.save(seller);
        assertNotNull(seller.getSellerId());
    }

    @Test
    void deleteSeller(){
        Seller seller1 = new Seller();

        seller1.setSellerFirstName("Seller");
        seller1.setSellerLastName("last name");
        seller1.setSellerEmail("selleremail@email.com");
        seller1.setSellerPassword("pws");
        seller1.setStoreName("my store");
        seller1.setBankName("My Bank");
        seller1.setAccountNumber("99002");

        sellerRepository.save(seller1);

        seller.setSellerFirstName("Seller");
        seller.setSellerLastName("last name");
        seller.setSellerEmail("selleremail@email.com");
        seller.setSellerPassword("pws");
        seller.setStoreName("my store");
        seller.setBankName("My Bank");
        seller.setAccountNumber("99002");

        sellerRepository.save(seller);

        assertEquals(sellerRepository.count(),2);
    }
}