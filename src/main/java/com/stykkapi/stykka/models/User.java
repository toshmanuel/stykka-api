package com.stykkapi.stykka.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "users")
@NoArgsConstructor

public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //    private List<Address> listOfAddresses;
    private String storeName;
    private String bankName;
    private String accountNumber;
//    private List<Product> listOfProduct;
}
