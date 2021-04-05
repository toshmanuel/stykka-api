package com.stykkapi.stykka.dtos;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String password;
    private String newPassword;
}
