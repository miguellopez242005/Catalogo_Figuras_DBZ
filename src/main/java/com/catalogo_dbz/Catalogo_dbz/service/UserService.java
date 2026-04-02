package com.catalogo_dbz.Catalogo_dbz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registrar(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
            .filter(u -> passwordEncoder.matches(password, u.getPassword()))
            .orElse(null);
    }
}