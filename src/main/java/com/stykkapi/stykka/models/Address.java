package com.stykkapi.stykka.models;

import lombok.Data;

@Data
public class Address {
    private String streetLine;
    private String city;
    private String state;
    private String country;
}
