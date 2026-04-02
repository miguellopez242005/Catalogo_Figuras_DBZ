package com.catalogo_dbz.Catalogo_dbz.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User nuevoUsuario = usuarioService.registrar(user);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
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