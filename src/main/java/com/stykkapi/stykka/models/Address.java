package com.stykkapi.stykka.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Address {
    @Id
    private String streetLine;
    private String city;
    private String state;
    private String country;
}
