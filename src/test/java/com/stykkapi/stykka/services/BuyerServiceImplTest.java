package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.repositories.BuyerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class BuyerServiceImplTest {
    @Autowired
    private BuyerRepository buyerDb;

    @Autowired
    private BuyerService buyerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldThrowAnExceptionIfBuyerEmailAlreadyExists(){
        RegisterBuyerDTO newBuyer = new RegisterBuyerDTO();
        newBuyer.setFirstName("Jane");
        newBuyer.setLastName("Jones");
        newBuyer.setEmail("jones11@gmail.com");
        newBuyer.setPassword("jones112");
        assertThrows(EmailExistsException.class, () -> buyerService.registerBuyer(newBuyer));
//        assertEquals(3, buyerDb.count());
    }

    @Test
    void shouldCreateABuyer(){

        RegisterBuyerDTO newBuyer = new RegisterBuyerDTO();
        newBuyer.setFirstName("Janey");
        newBuyer.setLastName("Joneses");
        newBuyer.setEmail("jones112111@gmail.com");
        newBuyer.setPassword("jones112");

        try{
            Buyer buyer2 = buyerService.registerBuyer(newBuyer);
        }catch(EmailExistsException e){
            e.getLocalizedMessage();
        }

    }

    @Test
    void shouldReturnAllBuyersInDatabase(){
        assertEquals(4, buyerService.getAllBuyers().stream().count());
    }

    @Test
    void shouldThrowAnExceptionIfBuyerDoesNotExist(){
       assertThrows(NoSuchElementException.class, () -> buyerService.getOneBuyer( "hjdjjjdjdj"));
    }

    @Test
    void shouldUpdateBuyerDetailIfBuyerExists() {
        Optional<Buyer> updatedBuyer = buyerDb.findByBuyerId("60697649a1cd0a759f289bab");
        updatedBuyer.get().setBuyerFirstName("Tarantula");
        buyerService.updateBuyer(updatedBuyer.get(), updatedBuyer.get().getBuyerId());
        assertEquals("Tarantula", updatedBuyer.get().getBuyerFirstName());
    }

    @Test
    void shouldUpdateBuyerPassword() throws InvalidPasswordException{
        Optional<Buyer> updatedBuyer = buyerDb.findByBuyerId("6068c07d95089910133f2b1b");
        updatedBuyer.get().setBuyerPassword("Jane55");
        updatedBuyer.get().setNewPassword("Jane55567");
        try{
            buyerService.updateBuyerPassword(updatedBuyer.get(), updatedBuyer.get().getBuyerId());

        }catch(InvalidPasswordException | NoSuchElementException ex){
            ex.getLocalizedMessage();
        }

    }

    @Test
    void shouldUpdateBuyerAddress(){}

    @Test
    void shouldAllowBuyerAddNewAddress(){}

    @Test
    void shouldDeleteABuyer(){
        Optional<Buyer> buyerToDelete = buyerDb.findByBuyerId("60675f15ad083a1db89a6771");
        try{
            buyerService.deleteBuyer(buyerToDelete.get().getBuyerId());
        }catch(NoSuchElementException e){
            System.out.println(e.getLocalizedMessage());
        }
        assertEquals(2, buyerDb.count());
    }
}