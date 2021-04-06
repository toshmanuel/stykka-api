package com.stykkapi.stykka.models;

import com.stykkapi.stykka.repositories.BuyerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Address address = new Address();
        List<Address> addresses = new ArrayList<>();
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
        Optional<Buyer> buyer1 = buyerDb.findById("606a033d7031b77f0064910f");
        buyer1.get().setBuyerFirstName("Jane");
        buyerDb.save(buyer1.get());
        assertEquals("Jane", buyer1.get().getBuyerFirstName());
    }

    @Test
    void shouldCheckBuyerDetails(){
        Optional<Buyer> buyer2 = buyerDb.findById("606a033d7031b77f0064910f");
        assertEquals("Jane", buyer2.get().getBuyerFirstName());
    }

    @Test
    void shouldDeleteABuyer(){
        Optional<Buyer> buyer2 = buyerDb.findById("606a033d7031b77f0064910e");
        buyerDb.deleteById(buyer2.get().getBuyerId());
        assertEquals(1, buyerDb.count());
    }

    @Test
    void shouldDeleteAllBuyers(){
        buyerDb.deleteAll();
        assertEquals(0, buyerDb.count());
    }
}