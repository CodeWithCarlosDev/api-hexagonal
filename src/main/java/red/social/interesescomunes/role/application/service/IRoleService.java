package red.social.interesescomunes.role.application.service;

import red.social.interesescomunes.role.application.command.CreateIRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRolCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAllRoles();
    Optional<Role> findRoleById(Long id);
    Role createRole(CreateIRoleCommand command);
    Role updateRole(Long id, UpdateRoleCommand command);
    void deleteRoleById(DeleteRolCommand command);
}
