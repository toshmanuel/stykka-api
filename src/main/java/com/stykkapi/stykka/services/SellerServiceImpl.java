package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterSellerDTO;
import com.stykkapi.stykka.exceptions.SellerException;
import com.stykkapi.stykka.models.Seller;
import com.stykkapi.stykka.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {


    @Autowired
    private SellerRepository sellerRepository;

    public Seller registerSeller(RegisterSellerDTO newSeller) throws SellerException{
        Seller seller = new Seller();

        Optional<Seller> registerSeller = sellerRepository.findSellerBySellerEmail(seller.getSellerEmail());

        if (registerSeller.isPresent()){
             throw new SellerException("Seller Already exist");
        }
        else
        {
        seller.setSellerFirstName(seller.getSellerFirstName());
        seller.setSellerLastName(seller.getSellerLastName());
        seller.setSellerEmail(seller.getSellerEmail());
        seller.setSellerPassword(seller.getSellerPassword());
        seller.setStoreName(seller.getStoreName());
        seller.setBankName(seller.getBankName());
        seller.setAccountNumber(seller.getAccountNumber());
    }
        return sellerRepository.save(seller);
    }

    @Override
    public List<Seller> findAllSeller() {
        return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> findBySellerByName(String storeName) {
        if (sellerRepository.findById(storeName).isPresent()) {
            return findBySellerByName(storeName);
        }else
            throw new NoSuchElementException("Seller not found");
    }

    @Override
    public Optional<Seller> deleteSellerById(String sellerId) {
        if (sellerRepository.findById(sellerId).isPresent()) {
            return sellerRepository.deleteBySellerId(sellerId);
        }
        else
            throw new NoSuchElementException(sellerId);
    }

    @Override
    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }
}
