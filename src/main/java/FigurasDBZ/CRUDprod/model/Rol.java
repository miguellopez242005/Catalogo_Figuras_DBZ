package FigurasDBZ.CRUDprod.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data // Si instalaste Lombok, esto crea getters y setters solo
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    
    private String rol;
}