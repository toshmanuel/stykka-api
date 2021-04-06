package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterSellerDTO;
import com.stykkapi.stykka.exceptions.SellerException;
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

    @Autowired
    SellerService sellerService;

    RegisterSellerDTO seller;

    @BeforeEach
    void setUp() {
        seller = new RegisterSellerDTO("", "", "", "", "", "", "");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createSeller(){


        seller.setSellerFirstName("Seller");
        seller.setSellerLastName("last name");
        seller.setSellerEmail("selleremail@uemail.com");
        seller.setSellerPassword("pws");
        seller.setStoreName("my store");
        seller.setBankName("My Bank");
        seller.setAccountNumber("99002");

        try {
            sellerService.saveSeller(seller);
            assertEquals(sellerRepository.count(), 6);
        } catch (SellerException e) {
            e.getLocalizedMessage();
        }
    }

    @Test
    void throwsException(){
        seller.setSellerFirstName("Seller");
        seller.setSellerLastName("last name");
        seller.setSellerEmail("selleremail@uemail.com");
        seller.setSellerPassword("pws");
        seller.setStoreName("my store");
        seller.setBankName("My Bank");
        seller.setAccountNumber("99002");
        assertThrows(SellerException.class, ()->sellerService.saveSeller(seller));
    }

    @Test
    void sellerCanBeDeleted() throws SellerException {
        sellerService.deleteBySellerId("606c1d1633156131f6be400d");
        assertEquals(sellerRepository.count(), 6);
    }
}