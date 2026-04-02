@Service
public class UsuarioService {
    @Autowired
    private UserRepository userRepository;

    public User registrar(User user) {
        return userRepository.save(user);
    }
}