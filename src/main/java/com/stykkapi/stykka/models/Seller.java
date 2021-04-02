package com.stykkapi.stykka.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sellers")
@NoArgsConstructor
public class Seller{
    @Id
    private String sellerId;
    private String sellerFirstName;
    private String sellerLastName;
    private String sellerEmail;
    private String sellerPassword;
    private String storeName;
    private String bankName;
    private String accountNumber;
//    private List<Product> listOfProduct;
}
