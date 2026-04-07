package com.catalogo_dbz.Catalogo_dbz.entity;

import jakarta.persistence.*;
import  lombok.*;
import java.time.LocalDate;

@Entity
@Table(name="factura")
@Data
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFactura;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "fecha")
    private LocalDate fecha;
}
