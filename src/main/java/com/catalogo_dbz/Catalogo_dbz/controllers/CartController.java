package com.catalogo_dbz.Catalogo_dbz.controllers;

import com.catalogo_dbz.Catalogo_dbz.dto.CartRequestDto;
import com.catalogo_dbz.Catalogo_dbz.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PostMapping
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartRequestDto request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cartService.addToCart(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(cartService.getCart(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{idFactura}")
    public ResponseEntity<?> removeFromCart(@PathVariable Integer idFactura) {
        try {
            cartService.removeFromCart(idFactura);
            return ResponseEntity.ok("Producto eliminado del carrito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{userId}/checkout")
    public ResponseEntity<?> checkout(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(cartService.checkout(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{userId}/history")
    public ResponseEntity<?> getPurchaseHistory(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(cartService.getPurchaseHistory(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}