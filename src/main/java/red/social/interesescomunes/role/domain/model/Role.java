package red.social.interesescomunes.role.domain.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private Long id;
    private String nombre;
    private String descripcion;
}
