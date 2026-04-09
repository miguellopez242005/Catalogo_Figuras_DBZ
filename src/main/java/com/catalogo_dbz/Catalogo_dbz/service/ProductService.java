package com.catalogo_dbz.Catalogo_dbz.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.catalogo_dbz.Catalogo_dbz.dto.ProductRequestDto;
import com.catalogo_dbz.Catalogo_dbz.dto.ProductResponseDto;
import com.catalogo_dbz.Catalogo_dbz.entity.Products;
import com.catalogo_dbz.Catalogo_dbz.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDto getById(Integer id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toResponse(product);
    }

    public ProductResponseDto create(ProductRequestDto request) {
        Products product = toEntity(request);
        return toResponse(productRepository.save(product));
    }

    public ProductResponseDto update(Integer id, ProductRequestDto request) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        return toResponse(productRepository.save(product));
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    private ProductResponseDto toResponse(Products product) {
        return new ProductResponseDto(
                product.getIdProduct(),
                product.getName(),
                product.getPrice(),
                product.getImage(),
                product.getDescription(),
                product.getStock()
        );
    }

    private Products toEntity(ProductRequestDto request) {
        Products product = new Products();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        return product;
    }
}