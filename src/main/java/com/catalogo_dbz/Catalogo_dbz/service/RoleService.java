package com.catalogo_dbz.Catalogo_dbz.service;

import com.catalogo_dbz.Catalogo_dbz.dto.RoleDTO;
import com.catalogo_dbz.Catalogo_dbz.entity.Role;
import com.catalogo_dbz.Catalogo_dbz.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private RoleDTO mapToDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setIdRol(role.getIdRol());
        dto.setName(role.getRol());
        return dto;
    }

    public List<RoleDTO> findAll() {
        try {
            return roleRepository.findAll().stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar roles: " + e.getMessage());
        }
    }

    public RoleDTO findById(Integer id) {
        try {
            Role role = roleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
            return mapToDTO(role);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el rol: " + e.getMessage());
        }
    }

    public RoleDTO save(Role role) {
        try {
            // Limpieza y formato (Estándar de Spring Security)
            role.setRol(role.getRol().toUpperCase().trim());
            // Guardamos (MySQL genera el ID autoincremental)
            Role nuevoRol = roleRepository.save(role);
            return mapToDTO(nuevoRol);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el rol: " + e.getMessage());
        }
    }

    public void delete(Integer id) {
        try {
            if (!roleRepository.existsById(id)) {
                throw new RuntimeException("El rol con ID " + id + " no existe.");
            }
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar (puede tener usuarios asociados): " + e.getMessage());
        }
    }
} 
