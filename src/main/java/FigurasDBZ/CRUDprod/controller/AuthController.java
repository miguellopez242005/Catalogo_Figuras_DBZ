package FigurasDBZ.CRUDprod.controller;

import FigurasDBZ.CRUDprod.dto.LoginRequestDto;
import FigurasDBZ.CRUDprod.entity.Users;
import FigurasDBZ.CRUDprod.repository.UserRepository;
import FigurasDBZ.CRUDprod.filter.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword())
        );

        Users user = userRepository.findByEmail(request.getEmail()).get();
        String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getRole().getRol()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}