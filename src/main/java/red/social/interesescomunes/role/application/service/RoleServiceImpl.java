package red.social.interesescomunes.role.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import red.social.interesescomunes.role.application.command.CreateIRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRolCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.application.query.FindRoleByIdQuery;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.repository.IRoleRepository;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleResponse;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl  implements IRoleService{

    private final IRoleRepository roleRepository;
    private final ApplicationEventPublisher eventPublisher;

    public RoleServiceImpl(IRoleRepository roleRepository, ApplicationEventPublisher eventPublisher){
        this.roleRepository = roleRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<RoleResponse> findAllRoles() {
        return roleRepository.findAll().stream()
            .map( role -> RoleResponse.builder()
            .id(role.getId())
            .nombre(role.getNombre())
            .descripcion(role.getDescripcion())
            .build() )
            .toList();
    }

    @Override
    public Optional<RoleResponse> findRoleById(FindRoleByIdQuery query) {
        RoleResponse roleResponse = this.roleRepository.findById(query.getId())
                .map( role -> RoleResponse.builder()
                        .id(role.getId())
                        .nombre(role.getNombre())
                        .descripcion(role.getDescripcion())
                        .build() )
                .orElseThrow(()-> new RuntimeException("No se encontro el rol con el id: " + query.getId()));
        return  Optional.of(roleResponse);
    }

    @Override
    public RoleResponse createRole(CreateIRoleCommand command) {
        Role role = Role.builder()
            .nombre(command.getNombre())
            .descripcion(command.getDescripcion())
            .eventPublisher(this.eventPublisher)
            .build();

        role.create(); // Publica el evento RoleCreatedEvent
        role = this.roleRepository.save(role);

        return RoleResponse.builder()
            .id(role.getId())
            .nombre(role.getNombre())
            .descripcion(role.getDescripcion())
            .build();
    }

    @Override
    public RoleResponse updateRole( UpdateRoleCommand command) {
        Role role =  Role.builder()
            .id(command.getId())
            .nombre(command.getNombre())
            .descripcion(command.getDescripcion())
            .eventPublisher(this.eventPublisher)
            .build();

        role.update(); // Publica el evento RoleUpdatedEvent
        this.roleRepository.save(role);

        return RoleResponse.builder()
            .id(role.getId())
            .nombre(role.getNombre())
            .descripcion(role.getDescripcion())
            .build();
    }

    @Override
    public void deleteRoleById(DeleteRolCommand command) {
        Role role =  this.roleRepository.findById(command.getId())
            .orElseThrow(()-> new RuntimeException("No se encontro el rol con el id: " + command.getId()));
        role.setEventPublisher(this.eventPublisher);
        role.delete(); // Publica el evento RoleDeletedEvent
        this.roleRepository.delete(command.getId());
    }
}
