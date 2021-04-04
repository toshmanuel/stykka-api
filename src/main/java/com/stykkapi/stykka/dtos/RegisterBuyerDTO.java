package com.stykkapi.stykka.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterBuyerDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
