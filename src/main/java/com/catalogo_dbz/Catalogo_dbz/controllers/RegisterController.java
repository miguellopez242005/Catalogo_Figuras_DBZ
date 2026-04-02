@RestController
@RequestMapping("/api/auth")
public class RegistrationController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return usuarioService.registrar(user); 
    }
}