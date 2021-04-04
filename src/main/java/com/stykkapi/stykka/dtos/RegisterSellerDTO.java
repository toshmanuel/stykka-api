package com.stykkapi.stykka.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@RequiredArgsConstructor
public class RegisterSellerDTO {
    @NotNull
    private String sellerFirstName;
    @NotNull
    private String sellerLastName;
    @NotNull
    private String sellerEmail;
    @NotNull
    private String sellerPassword;
    @NotNull
    private String storeName;
    @NotNull
    private String bankName;
    @NotNull
    private String accountNumber;
}
