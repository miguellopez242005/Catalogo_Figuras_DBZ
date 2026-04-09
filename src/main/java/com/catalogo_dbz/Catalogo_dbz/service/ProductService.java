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
        List<Products> products = productRepository.findAll();

        return products.stream().map(product -> {
            ProductResponseDto dto = new ProductResponseDto();
            dto.setIdProduct(product.getIdProduct());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setImage(product.getImage());
            dto.setDescription(product.getDescription());
            dto.setStock(product.getStock());
            return dto;
        }).collect(Collectors.toList());
    }

    public ProductResponseDto getById(Integer id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductResponseDto dto = new ProductResponseDto();
        dto.setIdProduct(product.getIdProduct());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());
        dto.setDescription(product.getDescription());
        dto.setStock(product.getStock());
        return dto;
    }

    public ProductResponseDto create(ProductRequestDto request) {
        Products product = new Products();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());

        Products saved = productRepository.save(product);

        ProductResponseDto dto = new ProductResponseDto();
        dto.setIdProduct(saved.getIdProduct());
        dto.setName(saved.getName());
        dto.setPrice(saved.getPrice());
        dto.setImage(saved.getImage());
        dto.setDescription(saved.getDescription());
        dto.setStock(saved.getStock());
        return dto;
    }

    public ProductResponseDto update(Integer id, ProductRequestDto request) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());

        Products saved = productRepository.save(product);

        ProductResponseDto dto = new ProductResponseDto();
        dto.setIdProduct(saved.getIdProduct());
        dto.setName(saved.getName());
        dto.setPrice(saved.getPrice());
        dto.setImage(saved.getImage());
        dto.setDescription(saved.getDescription());
        dto.setStock(saved.getStock());
        return dto;
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}