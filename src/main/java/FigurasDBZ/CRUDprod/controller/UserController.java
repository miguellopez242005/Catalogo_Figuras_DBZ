package FigurasDBZ.CRUDprod.controller;

import FigurasDBZ.CRUDprod.model.Users;
import FigurasDBZ.CRUDprod.service.UserService;
import org.springframework.beams.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins ="*")

public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Users> obtenerPerfil(@PathVariable Integer id, @RequestBody Users nuevosDatos ){
        try{
            Users actualizado = userService.actualizarDatos (id, nuevosDatos);
            return ResponseEntity.ok(actualizado);

        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}