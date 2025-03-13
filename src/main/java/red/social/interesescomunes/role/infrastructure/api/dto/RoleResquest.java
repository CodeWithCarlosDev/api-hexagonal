package red.social.interesescomunes.role.infrastructure.api.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResquest {
    private String nombre;
    private String descripcion;
}
