package com.catalogo_dbz.Catalogo_dbz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El precio es obligatorio")
    private Integer price;

    private String image;

    private String description;

    @NotNull(message = "El stock es obligatorio")
    private Integer stock;
}