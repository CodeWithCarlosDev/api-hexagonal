package red.social.interesescomunes.role.application.service;

import red.social.interesescomunes.role.application.command.CreateIRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRolCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.application.query.FindRoleByIdQuery;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleResponse;


import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<RoleResponse> findAllRoles();
    Optional<RoleResponse> findRoleById(FindRoleByIdQuery query);
    RoleResponse createRole(CreateIRoleCommand command);
    RoleResponse updateRole(UpdateRoleCommand command);
    void deleteRoleById(DeleteRolCommand command);
}
