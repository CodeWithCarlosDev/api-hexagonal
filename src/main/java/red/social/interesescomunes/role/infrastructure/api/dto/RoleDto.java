package red.social.interesescomunes.role.infrastructure.api.dto;

import lombok.*;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.model.TypeRole;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private TypeRole nombre;
    private String descripcion;

    public static RoleDto create(Role role){
        return RoleDto.builder()
            .id(role.getId())
            .nombre(role.getNombre())
            .descripcion(role.getDescripcion())
            .build();
    }
}
