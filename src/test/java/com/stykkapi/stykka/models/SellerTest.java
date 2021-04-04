package com.stykkapi.stykka.models;

import com.stykkapi.stykka.repositories.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class SellerTest {
    Seller seller;

    @Autowired
    SellerRepository sellerRepository;

    @BeforeEach
    void setUp() {
        seller = new Seller();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCreateNewSeller(){

        seller.setSellerFirstName("Dozie");
        seller.setSellerLastName("Uya");
        seller.setSellerEmail("seller@email.com");
        seller.setSellerPassword("my_password");
        seller.setStoreName("Dozie Enterprises");
        seller.setBankName("Ubi Ego Bank");
        seller.setAccountNumber("1234567898");

        sellerRepository.save(seller);
        assertEquals(sellerRepository.count(), 1);
    }

    @Test
    void shouldReadSellerDetail(){
        Optional<Seller> findSeller = sellerRepository.findById("60674cf2f2e968388d552ea9");
        assertEquals(findSeller.get().getSellerFirstName(), "Dozie");
    }

    @Test
    void shouldUpdateSellerDetail(){
        Optional <Seller> updateName = sellerRepository.findById("60674cf2f2e968388d552ea9");
        updateName.get().setSellerFirstName("Dozzy");
        sellerRepository.save(updateName.get());
        assertEquals("Dozzy", updateName.get().getSellerFirstName());
    }

    @Test
    void shouldDeleteSeller(){
        seller.setSellerFirstName("Mr Dozie");
        seller.setSellerLastName("Uya");
        seller.setSellerEmail("Mr.seller@email.com");
        seller.setSellerPassword("my_password");
        seller.setStoreName("Importer Exporter Enterprises");
        seller.setBankName("Money People Bank");
        seller.setAccountNumber("1234567898");

        sellerRepository.save(seller);
        sellerRepository.deleteById("60674cf2f2e968388d552ea9");
        assertEquals(sellerRepository.count(), 1);
    }

    @Test
    void shouldDeleteAllSeller(){
        sellerRepository.deleteAll();
        assertEquals(sellerRepository.count(), 0);
    }
}