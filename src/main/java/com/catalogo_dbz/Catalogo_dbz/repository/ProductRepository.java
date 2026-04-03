package com.catalogo_dbz.Catalogo_dbz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogo_dbz.Catalogo_dbz.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
}