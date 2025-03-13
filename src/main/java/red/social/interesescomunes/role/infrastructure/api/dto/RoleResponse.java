package red.social.interesescomunes.role.infrastructure.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private Long id;
    private String nombre;
    private String descripcion;
}
