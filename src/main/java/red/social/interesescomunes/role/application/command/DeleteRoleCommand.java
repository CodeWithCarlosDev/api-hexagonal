package red.social.interesescomunes.role.application.command;

import lombok.*;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;

@Getter
@Setter
@Builder
@ToString
public class DeleteRoleCommand {
    private Long id;

    public static DeleteRoleCommand create(Long idRole){
        return DeleteRoleCommand.builder()
                .id(idRole)
                .build();
    }
}
