package com.catalogo_dbz.Catalogo_dbz.service;

import com.catalogo_dbz.Catalogo_dbz.dto.RefreshTokenResponseDTO;
import com.catalogo_dbz.Catalogo_dbz.dto.UserDTO;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import com.catalogo_dbz.Catalogo_dbz.filter.JwtUtil;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager; // IMPORT CRÍTICO
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // IMPORT CRÍTICO
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserDTO register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Este email ya está en uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User nuevoUsuario = userRepository.save(user);

        return mapToDTO(nuevoUsuario, null);
    }

    public UserDTO login(String email, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        User userFound = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwt = jwtUtil.generateToken(
            userFound.getEmail(),
            userFound.getRole().getRol()
        );

        return mapToDTO(userFound, jwt);
    }

    public RefreshTokenResponseDTO refreshToken(String token) {
        String jwt = jwtUtil.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setJwt(jwt);
        return response;
    }

    private UserDTO mapToDTO(User user, String token) {
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRoleName(user.getRole().getRol());
        dto.setToken(token);
        return dto;
    }
}