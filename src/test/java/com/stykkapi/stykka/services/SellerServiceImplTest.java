package com.stykkapi.stykka.services;

import com.stykkapi.stykka.models.Seller;
import com.stykkapi.stykka.repositories.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}