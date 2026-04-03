package com.catalogo_dbz.Catalogo_dbz.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo_dbz.Catalogo_dbz.entity.User;
import com.catalogo_dbz.Catalogo_dbz.repository.UserRepository;

@Service
public class UsuarioService {
    @Autowired
    private UserRepository userRepository;

    public User registrar(User user) {
        return userRepository.save(user);
    }
}