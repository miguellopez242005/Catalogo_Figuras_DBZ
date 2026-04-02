package FigurasDBZ.CRUDprod.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Integer idProduct;
    private String name;
    private Integer price;
    private String image;
    private String description;
    private Integer stock;
}