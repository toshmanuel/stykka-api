package com.stykkapi.stykka.dtos;

import lombok.Data;

@Data
public class RegisterBuyerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
