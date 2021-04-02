package com.stykkapi.stykka.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterSellerDTO {

    private String sellerFirstName;
    private String sellerLastName;
    private String sellerEmail;
    private String sellerPassword;
    private String storeName;
    private String bankName;
    private String accountNumber;
}
