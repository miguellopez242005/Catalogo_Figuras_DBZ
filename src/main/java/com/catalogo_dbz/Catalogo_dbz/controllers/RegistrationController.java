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
    private UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}