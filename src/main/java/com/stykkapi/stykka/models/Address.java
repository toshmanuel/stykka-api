package com.stykkapi.stykka.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="addresses")
@Data
public class Address {
    @Id
    private String addressId;
    private String streetLine;
    private String city;
    private String state;
    private String country;
}
