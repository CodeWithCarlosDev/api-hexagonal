package red.social.interesescomunes.role.infrastructure.persistence.mysql.jpa;


import jakarta.persistence.*;
import lombok.*;
import red.social.interesescomunes.role.domain.model.TypeRole;


@Getter
@Setter
@Builder
@Entity
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Enumerated(EnumType.STRING)
   private TypeRole nombre;
   private String descripcion;
}
