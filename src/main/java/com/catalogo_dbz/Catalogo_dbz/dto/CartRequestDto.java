package com.catalogo_dbz.Catalogo_dbz.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartRequestDto {

    @NotNull(message = "El producto es obligatorio")
    private Integer productId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer userId;
}
