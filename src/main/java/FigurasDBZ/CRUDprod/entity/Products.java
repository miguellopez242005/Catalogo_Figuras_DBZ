package FigurasDBZ.CRUDprod.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    private String image;

    private String description;

    @NotNull
    private Integer stock;
}