package com.stykkapi.stykka.dtos;

import lombok.Data;

@Data
public class AddressDTO {
    private String streetLine;
    private String city;
    private String state;
    private String country;
}
