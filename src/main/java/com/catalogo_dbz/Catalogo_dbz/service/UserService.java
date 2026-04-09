package com.catalogo_dbz.Catalogo_dbz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogo_dbz.Catalogo_dbz.dto.UserDTO;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
    
        if (user.getRole() != null) {
            dto.setRoleName(user.getRole().getRol());
        }
        return dto;
    }

    public List<UserDTO> findAll() {
        try {
            return userRepository.findAll().stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar usuarios: " + e.getMessage());
        }
    }

    public UserDTO findById(Integer id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
            return mapToDTO(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario: " + e.getMessage());
        }
    }

    public UserDTO update(Integer id, User userDetails) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se encontró el usuario para actualizar"));
            
            user.setName(userDetails.getName());
            user.setPhone(userDetails.getPhone());
            user.setEmail(userDetails.getEmail());
             
            if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }

            return mapToDTO(userRepository.save(user));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void delete(Integer id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new RuntimeException("El usuario no existe");
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage());
        }
    }
}