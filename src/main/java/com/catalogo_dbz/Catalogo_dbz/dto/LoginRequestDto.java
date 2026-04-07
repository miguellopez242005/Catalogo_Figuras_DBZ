package com.catalogo_dbz.Catalogo_dbz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotNull(message = "La contraseña es obligatoria")
    private String password;
}