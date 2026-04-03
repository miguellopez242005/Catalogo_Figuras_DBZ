package FigurasDBZ.CRUDprod.services;

import FigurasDBZ.CRUDprod.entity.Users;
import FigurasDBZ.CRUDprod.model.User;
import FigurasDBZ.CRUDprod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para obtener los datos actuales del usuario
    public Users obtenerPorId(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Método principal para que el usuario modifique sus datos
    public Users actualizarDatos(Integer id, Users datosNuevos) {
        Optional<Users> usuarioExistente = userRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Users user = usuarioExistente.get();
            
            // Actualizamos solo los campos permitidos
            user.setName(datosNuevos.getName());
            user.setEmail(datosNuevos.getEmail());
            user.setPhone(datosNuevos.getPhone());
            
            // Si el usuario envía una nueva contraseña, la actualizamos
            if (datosNuevos.getPassword() != null && !datosNuevos.getPassword().isEmpty()) {
                user.setPassword(datosNuevos.getPassword());
            }

            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuario con ID " + id + " no encontrado.");
        }
    }
}