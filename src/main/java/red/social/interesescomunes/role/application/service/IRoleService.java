package red.social.interesescomunes.role.application.service;

import red.social.interesescomunes.role.application.command.CreateRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRoleCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.application.query.FindRoleByIdQuery;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;


import java.util.List;
import java.util.Optional;

// interfaz que define los casos del rol del usuario en la aplicacion
public interface IRoleService {
    Optional<List<RoleDto>> findAllRoles();
    Optional<RoleDto> findRoleById(FindRoleByIdQuery query);
    RoleDto createRole(CreateRoleCommand command);
    RoleDto updateRole(UpdateRoleCommand command);
    void deleteRoleById(DeleteRoleCommand command);
}
