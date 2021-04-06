package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.AddressDTO;
import com.stykkapi.stykka.dtos.ChangePasswordDTO;
import com.stykkapi.stykka.dtos.LoginDTO;
import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.*;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.repositories.AddressRepository;
import com.stykkapi.stykka.repositories.BuyerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerServiceImplTest {

    @Autowired
    private BuyerRepository buyerDb;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private AddressRepository addressDb;

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
    void shouldAllowBuyerChangePassword()  {
        ChangePasswordDTO passwordDTO = new ChangePasswordDTO();
        Optional<Buyer> foundBuyer = buyerDb.findByBuyerId("606a08fb4d247442f43de36c");
        passwordDTO.setOldPassword("234561");
        passwordDTO.setNewPassword("");
        try{
            buyerService.changePassword(passwordDTO,"606a08fb4d247442f43de36c");
        }catch(InvalidPasswordException e){
            System.out.println(e.getLocalizedMessage());
        }
        assertEquals("234561", foundBuyer.get().getBuyerPassword());
    }


    @Test
    void shouldUpdateBuyerDetailIfBuyerExists() {

//        FrontEnd
        Buyer buyer = new Buyer();
        buyer.setBuyerFirstName("Good");
        buyer.setBuyerLastName("Bad");
        buyer.setBuyerEmail("Good@Bad.com");
//        Service
        buyerService.updateBuyer(buyer, "606a033d7031b77f0064910f");

//        Assert
        assertEquals("Good", buyer.getBuyerFirstName());
    }

    @Test
    void shouldEncryptBuyerPassword(){}

    @Test
    void shouldCreateNewAddAddress(){
        AddressDTO buyerAddress = new AddressDTO();
        buyerAddress.setStreetLine("19 oyediran street");
        buyerAddress.setCity("Yaba");
        buyerAddress.setState("Lagos");
        buyerAddress.setCountry("Nigeria");

//        buyerService.createAddress(buyerAddress,"606a08fb4d247442f43de36c");

        assertEquals("Nigeria", buyerAddress.getCountry());
    }

    @Test
    void shouldAllowBuyerAddNewAddress(){
        AddressDTO buyerAddress = new AddressDTO();
        buyerAddress.setStreetLine("19 oyediran street");
        buyerAddress.setCity("Yaba");
        buyerAddress.setState("Lagos");
        buyerAddress.setCountry("Nigeria");
        try{
            buyerService.addAddress(buyerAddress,"606a08fb4d0247442f43de36c");
//            assertEquals(4, addressDb.count());
        }catch(BuyerNotFoundException e){
            e.getLocalizedMessage();
        }
    }

    @SneakyThrows
    @Test
    void shouldAllowUserLogin() {
        LoginDTO existingBuyer = new LoginDTO();
        existingBuyer.setEmail("jones112111@gmail.com");
        existingBuyer.setPassword("234561");

        try{
            buyerService.loginBuyer(existingBuyer);
        }catch(InvalidPasswordException | InvalidEmailException e){
            System.out.println(e.getLocalizedMessage());
        }

        assertTrue(buyerService.loginBuyer(existingBuyer));
    }

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