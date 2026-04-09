package com.catalogo_dbz.Catalogo_dbz.repository;

import com.catalogo_dbz.Catalogo_dbz.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Integer>{
    List<Factura> findByUserIdUserAndFechaIsNull(Integer userId);
     List<Factura> findByUserIdUserAndFechaIsNotNullOrderByFechaDesc(Integer userId);
}
