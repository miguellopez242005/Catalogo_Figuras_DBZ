@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    private String name;
    private String email;
    private String password;
    private String phone;
    @ManyToOne 
    @JoinColumn(name = "id_rol")
    private Role role;
}