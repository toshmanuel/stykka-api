package com.stykkapi.stykka.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDTO {
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
