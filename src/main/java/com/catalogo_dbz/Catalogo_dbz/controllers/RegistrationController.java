package com.catalogo_dbz.Catalogo_dbz.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.catalogo_dbz.Catalogo_dbz.dto.UserDTO;
import com.catalogo_dbz.Catalogo_dbz.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {


    @Autowired
    private AuthService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            UserDTO dto = usuarioService.register(user);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el registro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        try {
            UserDTO dto = usuarioService.login(loginData.getEmail(), loginData.getPassword());
            
            if (dto != null) {                
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
}