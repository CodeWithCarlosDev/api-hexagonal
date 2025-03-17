package red.social.interesescomunes.role.application.command;

import lombok.*;
import red.social.interesescomunes.role.domain.model.TypeRole;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateRoleCommand {
    private TypeRole nombre;
    private String descripcion;

    public static CreateRoleCommand create(RoleDto role){
        return CreateRoleCommand.builder()
                .nombre(role.getNombre())
                .descripcion(role.getDescripcion())
                .build();
    }

}
