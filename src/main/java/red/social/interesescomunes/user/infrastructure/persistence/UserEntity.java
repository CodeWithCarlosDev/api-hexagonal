package red.social.interesescomunes.user.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.persistence.mysql.jpa.RoleEntity;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String clave;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"), // Columna para el ID del usuario
            inverseJoinColumns = @JoinColumn(name = "rol_id") // Columna para el ID del rol
    )
    private List<RoleEntity> roles;
}
