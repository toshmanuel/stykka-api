package com.stykkapi.stykka.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeBuyerPasswordDTO {
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
