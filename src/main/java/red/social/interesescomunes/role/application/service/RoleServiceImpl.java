package red.social.interesescomunes.role.application.service;

import org.springframework.stereotype.Service;
import red.social.interesescomunes.role.application.command.CreateIRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRolCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.repository.IRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl  implements IRoleService{

    private final IRoleRepository roleRepository;

    public RoleServiceImpl(IRoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        Role role =  this.roleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el rol :" + id));
        return  Optional.of(role);
    }

    @Override
    public Role createRole(CreateIRoleCommand command) {
        Role role = Role.builder()
                        .nombre(command.getNombre())
                        .descripcion(command.getDescripcion())
                        .build();
        return this.roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, UpdateRoleCommand command) {
        Role role =  this.roleRepository.findById(id)
                .map( roleItem -> new Role(command.getId(),command.getNombre(),command.getDescripcion()) )
                .orElseThrow(()-> new RuntimeException("No se encontro el rol :" + command.getId()));

        return this.roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(DeleteRolCommand command) {
        this.roleRepository.findById(command.getId())
                .orElseThrow(()-> new RuntimeException("No se encontro el rol :" + command.getId()));
        this.roleRepository.delete(command.getId());
    }
}
