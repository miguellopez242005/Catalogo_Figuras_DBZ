package com.catalogo_dbz.Catalogo_dbz.entity;
import org.aspectj.lang.annotation.RequiredTypes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@RequiredArgsConstructor
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