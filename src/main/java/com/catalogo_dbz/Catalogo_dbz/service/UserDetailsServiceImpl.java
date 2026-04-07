package com.catalogo_dbz.Catalogo_dbz.service;
import org.springframework.stereotype.Service;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
@RequiredArgsConstructor 
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().getRol())
                .build();
    }
}