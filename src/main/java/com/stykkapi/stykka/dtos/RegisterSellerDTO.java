package com.stykkapi.stykka.dtos;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data

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
