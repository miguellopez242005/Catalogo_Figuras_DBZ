package com.catalogo_dbz.Catalogo_dbz.service;

import com.catalogo_dbz.Catalogo_dbz.dto.CartRequestDto;
import com.catalogo_dbz.Catalogo_dbz.dto.CartResponseDto;
import com.catalogo_dbz.Catalogo_dbz.entity.Factura;
import com.catalogo_dbz.Catalogo_dbz.entity.Products;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import com.catalogo_dbz.Catalogo_dbz.repository.FacturaRepository;
import com.catalogo_dbz.Catalogo_dbz.repository.ProductRepository;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final FacturaRepository facturaRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartResponseDto addToCart(CartRequestDto request) {
        Products product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (product.getStock() <= 0) {
            throw new RuntimeException("Producto sin stock disponible");
        }

        Factura factura = new Factura();
        factura.setProduct(product);
        factura.setUser(user);
        factura.setFecha(null); 

        return toResponse(facturaRepository.save(factura));
    }
    public List<CartResponseDto> getCart(Integer userId) {
        return facturaRepository
                .findByUserIdUserAndFechaIsNull(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    public void removeFromCart(Integer idFactura) {
        Factura factura = facturaRepository.findById(idFactura)
                .orElseThrow(() -> new RuntimeException("Item no encontrado en el carrito"));

        if (factura.getFecha() != null) {
            throw new RuntimeException("Este item ya fue comprado, no se puede eliminar");
        }

        facturaRepository.deleteById(idFactura);
    }
    @Transactional
    public List<CartResponseDto> checkout(Integer userId) {
        List<Factura> cartItems = facturaRepository
                .findByUserIdUserAndFechaIsNull(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }
        for (Factura item : cartItems) {
            if (item.getProduct().getStock() <= 0) {
                throw new RuntimeException(
                    "Sin stock para: " + item.getProduct().getName()
                );
            }
        }
        for (Factura item : cartItems) {
            Products product = item.getProduct();
            product.setStock(product.getStock() - 1);
            productRepository.save(product);

            item.setFecha(LocalDate.now());
            facturaRepository.save(item);
        }
        return cartItems.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    public List<CartResponseDto> getPurchaseHistory(Integer userId) {
        return facturaRepository
                .findByUserIdUserAndFechaIsNotNullOrderByFechaDesc(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    private CartResponseDto toResponse(Factura factura) {
        return new CartResponseDto(
                factura.getIdFactura(),
                factura.getProduct().getIdProduct(),
                factura.getProduct().getName(),
                factura.getProduct().getPrice(),
                factura.getProduct().getImage(),
                factura.getUser().getIdUser(),
                factura.getUser().getName(),
                factura.getFecha()
        );
    }
}