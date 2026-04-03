package com.catalogo_dbz.Catalogo_dbz.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}