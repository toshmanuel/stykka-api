package com.stykkapi.stykka.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Data
@Document(collection = "buyers")
@NoArgsConstructor
public class Buyer{
    @Id
    private String buyerId;
    private String buyerFirstName;
    private String buyerLastName;
    private String buyerEmail;
    private String buyerPassword;
    private String newPassword;
    private HashSet<Address> addresses = new HashSet<>();
}
