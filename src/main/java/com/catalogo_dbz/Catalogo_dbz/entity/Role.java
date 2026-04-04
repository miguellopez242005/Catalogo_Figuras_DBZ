package com.catalogo_dbz.Catalogo_dbz.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;
    private String rol;
    public void setRol(String rol) {
        this.rol = (rol != null) ? rol.toUpperCase().trim() : null;
    }
}