package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.AddressDTO;
import com.stykkapi.stykka.dtos.ChangePasswordDTO;
import com.stykkapi.stykka.dtos.LoginDTO;
import com.stykkapi.stykka.dtos.RegisterBuyerDTO;
import com.stykkapi.stykka.exceptions.BuyerNotFoundException;
import com.stykkapi.stykka.exceptions.EmailExistsException;
import com.stykkapi.stykka.exceptions.InvalidEmailException;
import com.stykkapi.stykka.exceptions.InvalidPasswordException;
import com.stykkapi.stykka.models.Address;
import com.stykkapi.stykka.models.Buyer;
import com.stykkapi.stykka.repositories.AddressRepository;
import com.stykkapi.stykka.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private BuyerRepository buyerDb;

    @Autowired
    private AddressRepository addressDb;

    /**
     * This registers new buyers
     * RegisterBuyerDTO - frontend (form for user inputs)
     * Create an instance of the buyer model
     *
     * Use optionalBuyer to check if new buyer email already exists in the database(buyerDb)
     *
     * if buyer email exists throw EmailExistsException else store buyer inputs in database
     * @author  saucekode
     * @param newBuyer
     * @return buyer
     * @version 1.0
     * @since   2021-04-5
     */
    @Override
    public Buyer registerBuyer(RegisterBuyerDTO newBuyer) throws EmailExistsException {
        Buyer buyer = new Buyer();

        Optional<Buyer> optionalBuyer = buyerDb.findByBuyerEmail(buyer.getBuyerEmail());

        if(optionalBuyer.isPresent()) throw new EmailExistsException("Email already exists");

        else{
            buyer.setBuyerFirstName(newBuyer.getFirstName());
            buyer.setBuyerLastName(newBuyer.getLastName());
            buyer.setBuyerEmail(newBuyer.getEmail());
            buyer.setBuyerPassword(newBuyer.getPassword());
            saveBuyerToDb(buyer);
        }
        return buyer;
    }

    /**
     * This saves data to repo
     * @param buyer
     * @author saucekode
     * @since 2021-05-3
     * @return
     */
    private Buyer saveBuyerToDb(Buyer buyer){
        buyerDb.save(buyer);
        return buyer;
    }

    /**
     * This gets every buyer from the database
     * @author saucekode
     * @since 2021-05-3
     * @return all buyers
     */
    @Override
    public List<Buyer> getAllBuyers() {
        return buyerDb.findAll();
    }

    /**
     * Get a buyer by their id
     * @param buyerId
     * @return the found buyer
     */
    @Override
    public Optional<Buyer> getOneBuyer(String buyerId){
        if(buyerDb.findByBuyerId(buyerId).isPresent()){
            return buyerDb.findByBuyerId(buyerId);
        }else{
            throw new NoSuchElementException("Buyer with id: " + buyerId + " does not exist");
        }
    }

    /**
     * This finds a buyer in the database and allows them update their data
     * if buyer is not found in db, throw NoSuchElementException found,
     * else update their data and save in db
     * @param buyerToUpdate, buyerId
     * @return the found buyer
     */
    @Override
    public Buyer updateBuyer(Buyer buyerToUpdate, String buyerId){
        Optional<Buyer> foundBuyer = buyerDb.findByBuyerId(buyerId);
        if(foundBuyer.isEmpty()){
            throw new NoSuchElementException("Buyer with id: " + buyerId + " not found");
        }else{
            foundBuyer.get().setBuyerFirstName(buyerToUpdate.getBuyerFirstName());
            foundBuyer.get().setBuyerLastName(buyerToUpdate.getBuyerLastName());
            foundBuyer.get().setBuyerEmail(buyerToUpdate.getBuyerEmail());
            saveBuyerToDb(foundBuyer.get());
            return foundBuyer.get();
        }
    }

    /**
     * This updates the password of a buyer
     * Buyer has to provide old password,
     * then the new password can be set
     *
     * if new password is empty on save,
     * set password back to old password
     * @author saucekode
     * @since 2021-04-5
     * @param buyerPasswordDTO buyerId
     */
    @Override
    public void changePassword(ChangePasswordDTO buyerPasswordDTO, String buyerId) throws InvalidPasswordException {
        Optional<Buyer> foundBuyer = Optional.ofNullable(buyerDb.findByBuyerId(buyerId)
                                        .orElseThrow(() -> new NoSuchElementException("Buyer with id: " + buyerId + " does not exist")));

        String oldPassword = foundBuyer.get().getBuyerPassword();

        if(oldPassword.equals(buyerPasswordDTO.getOldPassword())){
            foundBuyer.get().setBuyerPassword(buyerPasswordDTO.getNewPassword());

            if(buyerPasswordDTO.getNewPassword().isEmpty()){
                foundBuyer.get().setBuyerPassword(oldPassword);
            }
        }else{
            throw new InvalidPasswordException("Provide your old password");
        }

        saveBuyerToDb(foundBuyer.get());
    }


    /**
     * This deletes a buyer by id
     * @param buyerId
     * @author saucekode
     * @return delete buyer from database
     */
    @Override
    public Optional<Buyer> deleteBuyer(String buyerId){
        if(buyerDb.findByBuyerId(buyerId).isPresent()){
           return buyerDb.deleteBuyerByBuyerId(buyerId);
        }else{
            throw new NoSuchElementException("Buyer does not exist");
        }
    }

    /**
     * This allows an existent buyer login
     * @param existingBuyer
     */
    @Override
    public boolean loginBuyer(LoginDTO existingBuyer) throws InvalidPasswordException, InvalidEmailException {
        Optional<Buyer> foundBuyerEmail = buyerDb.findByBuyerEmail(existingBuyer.getEmail());
        Optional<Buyer> foundBuyerPassword = buyerDb.findByBuyerPassword(existingBuyer.getPassword());

        if(foundBuyerEmail.isEmpty()){
            throw new InvalidEmailException("Invalid email");
        }

        if(foundBuyerPassword.isEmpty()){
            throw new InvalidPasswordException("Invalid password");
        }
        return true;
    }


    /**
     * This represents the create address form
     * if buyer exists, allow them fill the address details
     * @param addressDTO, buyerId
     * @return saved address to the db
     * @author saucekode
     * @since 2021-06-4
     */

    private  Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreetLine(addressDTO.getStreetLine());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

    /**
     *  This adds an existing address to the buyer address hashset
     * @param buyerId
     * @return address info saved in the buyer db
     * @author saucekode
     * @since 2021-06-4
     */
    @Override
    public void addAddress(AddressDTO addressDTO, String buyerId) throws BuyerNotFoundException {

        Optional<Buyer> optionalBuyer = buyerDb.findByBuyerId(buyerId);

        if(optionalBuyer.isPresent()){
            Buyer buyer = optionalBuyer.get();
            Address createdAddress = createAddress(addressDTO);
            createdAddress.setUserId(buyerId);
            addressDb.save(createdAddress);

            HashSet<Address> addresses = buyer.getAddresses();
            addresses.add(createdAddress);
            buyer.setAddresses(addresses);
            buyerDb.save(buyer);
        }else{
            throw new BuyerNotFoundException("Buyer not found");
        }
    }
}
