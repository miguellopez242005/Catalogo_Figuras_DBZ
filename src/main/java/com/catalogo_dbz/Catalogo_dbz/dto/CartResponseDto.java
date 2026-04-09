package com.catalogo_dbz.Catalogo_dbz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {

    private Integer idFactura;
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private String productImage;
    private Integer userId;
    private String userName;
    private LocalDate fecha;
    }