package com.catalogo_dbz.Catalogo_dbz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleDTO {
    @NotNull(message = "El idRol no existe")
    private Integer idRol;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
}