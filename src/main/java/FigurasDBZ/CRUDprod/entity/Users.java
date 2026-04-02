package FigurasDBZ.CRUDprod.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String name;
    private String email;
    private String password;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Role role;
}