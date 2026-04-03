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
import com.catalogo_dbz.Catalogo_dbz.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {


    @Autowired
    private UserService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User nuevoUsuario = usuarioService.registrar(user);
            UserDTO dto = new UserDTO();
            dto.setIdUser(nuevoUsuario.getIdUser());
            dto.setName(nuevoUsuario.getName());
            dto.setEmail(nuevoUsuario.getEmail());
            dto.setPhone(nuevoUsuario.getPhone());
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el registro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        try {
            User user = usuarioService.login(loginData.getEmail(), loginData.getPassword());
            
            if (user != null) {
                UserDTO dto = new UserDTO();
                dto.setIdUser(user.getIdUser());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                dto.setPhone(user.getPhone());
                
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
}