package com.stykkapi.stykka.models;

import com.stykkapi.stykka.repositories.BuyerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BuyerTest {

    @Autowired
    private BuyerRepository buyerDb;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCreateANewBuyer(){
        Buyer buyer1 = new Buyer();
        buyer1.setBuyerFirstName("Angelica");
        buyer1.setBuyerLastName("Junaid");
        buyer1.setBuyerPassword("buyer112");
        buyer1.setBuyerEmail("buyer@gmail.com");
        buyerDb.save(buyer1);
        assertNotNull(buyer1.getBuyerId());

        Buyer buyer2 = new Buyer();
        buyer2.setBuyerFirstName("Amaka");
        buyer2.setBuyerLastName("Babygeh");
        buyer2.setBuyerPassword("buyergeh112");
        buyer2.setBuyerEmail("buye2r@gmail.com");
        buyerDb.save(buyer2);
        assertNotNull(buyer2.getBuyerId());
    }

    @Test
    void shouldUpdateBuyerDetails(){
        Optional<Buyer> buyer1 = buyerDb.findById("60674f6f3ad01f4396e77b41");
        buyer1.get().setBuyerFirstName("Jane");
        buyerDb.save(buyer1.get());
        assertEquals("Jane", buyer1.get().getBuyerFirstName());
    }

    @Test
    void shouldCheckBuyerDetails(){
        Optional<Buyer> buyer2 = buyerDb.findById("60674f6f3ad01f4396e77b41");
        assertEquals("Jane", buyer2.get().getBuyerFirstName());
    }

    @Test
    void shouldDeleteABuyer(){
        Optional<Buyer> buyer2 = buyerDb.findById("60675175dc380d6ed3a7a71b");
        buyerDb.deleteById(buyer2.get().getBuyerId());
        assertEquals(1, buyerDb.count());
    }

    @Test
    void shouldDeleteAllBuyers(){
        buyerDb.deleteAll();
        assertEquals(0, buyerDb.count());
    }
}