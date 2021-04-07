package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.RegisterSellerDTO;
import com.stykkapi.stykka.exceptions.SellerException;
import com.stykkapi.stykka.models.Seller;
import com.stykkapi.stykka.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {


    private SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void saveSeller(RegisterSellerDTO newSeller) throws SellerException{
        Optional<Seller> optionalSeller = sellerRepository.findSellerBySellerEmail(newSeller.getSellerEmail());

        if (optionalSeller.isPresent()){
            throw new SellerException("Seller Already exist");
        }
        Seller seller = new Seller();

        seller.setSellerFirstName(newSeller.getSellerFirstName());
        seller.setSellerLastName(newSeller.getSellerLastName());
        seller.setSellerEmail(newSeller.getSellerEmail());
        seller.setSellerPassword(newSeller.getSellerPassword());
        seller.setStoreName(newSeller.getStoreName());
        seller.setBankName(newSeller.getBankName());
        seller.setAccountNumber(newSeller.getAccountNumber());

        sellerRepository.save(seller);
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
    public void deleteBySellerId(String sellerId) throws SellerException {
        if (sellerRepository.findById(sellerId).isPresent()) {
            sellerRepository.deleteBySellerId(sellerId);
        }
        else
            throw new SellerException("No seller found with that Id");
    }

    @Override
    public void deleteAll(Seller seller) throws SellerException {
        sellerRepository.deleteAll();
    }
}
