package com.catalogo_dbz.Catalogo_dbz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoDbzApplication {

    @Autowired
    private UserRepository userRepository;

    public User registrar(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public User login(String email, String password) throws Exception {
        try {
            return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
        } catch (Exception e) {
            throw new Exception("Error en la autenticación: " + e.getMessage());
        }
    }

}
