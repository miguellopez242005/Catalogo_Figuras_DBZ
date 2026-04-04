package com.catalogo_dbz.Catalogo_dbz.service;

import com.catalogo_dbz.Catalogo_dbz.dto.RefreshTokenResponseDTO;
import com.catalogo_dbz.Catalogo_dbz.dto.UserDTO;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import com.catalogo_dbz.Catalogo_dbz.filter.JwtUtil;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserDTO register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Este email ya está en uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User nuevoUsuario = userRepository.save(user);

        UserDTO dto = new UserDTO();
        dto.setIdUser(nuevoUsuario.getIdUser());
        dto.setName(nuevoUsuario.getName());
        dto.setEmail(nuevoUsuario.getEmail());
        dto.setPhone(nuevoUsuario.getPhone());
        dto.setRoleName(nuevoUsuario.getRole().getRol());
        return dto;
    }

    public UserDTO login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("Este usuario no se encuentra registrado");
        }

        User userFound = user.get();

        if (!passwordEncoder.matches(password, userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String jwt = jwtUtil.generateToken(
            userFound.getEmail(),
            userFound.getRole().getRol()
        );

        UserDTO dto = new UserDTO();
        dto.setIdUser(userFound.getIdUser());
        dto.setName(userFound.getName());
        dto.setEmail(userFound.getEmail());
        dto.setPhone(userFound.getPhone());
        dto.setRoleName(userFound.getRole().getRol());
        dto.setToken(jwt);
        return dto;
    }

    /**
     * Este método es para el refresco del token
     * @param token jwt viejo
     * @return nuevo token
     */
    public RefreshTokenResponseDTO refreshToken(String token) {
        String jwt = jwtUtil.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setJwt(jwt);
        return response;
    }
}