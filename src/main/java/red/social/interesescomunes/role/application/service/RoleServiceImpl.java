package red.social.interesescomunes.role.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import red.social.interesescomunes.role.application.command.CreateRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRoleCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.application.query.FindRoleByIdQuery;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.repository.IRoleRepository;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;

import java.util.List;
import java.util.Optional;

@Service
// Servicio que procesa los casos de uso del rol en la aplicacion
public class RoleServiceImpl  implements IRoleService{

    private final IRoleRepository roleRepository;
    private final ApplicationEventPublisher eventPublisher;

    public RoleServiceImpl(IRoleRepository roleRepository, ApplicationEventPublisher eventPublisher){
        this.roleRepository = roleRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<List<RoleDto>> findAllRoles() {
        List<Role> roles  = roleRepository.findAll();

        if(roles.isEmpty()){
            return Optional.empty();
        }

        List<RoleDto> roleDtos  = roles.stream()
            .map( RoleDto::create)
            .toList();

        return Optional.of(roleDtos);
    }

    @Override
    public Optional<RoleDto> findRoleById(FindRoleByIdQuery query) {
        RoleDto roleResponse = this.roleRepository.findById(query.getId())
                .map( RoleDto::create )
                .orElseThrow(()-> new RuntimeException("No se encontro el rol con el id: " + query.getId()));

        return  Optional.of(roleResponse);
    }

    @Override
    public RoleDto createRole(CreateRoleCommand command) {
        Role role = Role.builder()
            .nombre(command.getNombre())
            .descripcion(command.getDescripcion())
            .build();

        Role roleCreated = this.roleRepository.save(role);
        roleCreated.setEventPublisher(this.eventPublisher);
        roleCreated.create(); // Publica el evento RoleCreatedEvent

        return RoleDto.create(roleCreated);
    }

    @Override
    public RoleDto updateRole(UpdateRoleCommand command) {
        Role role =  Role.builder()
            .id(command.getId())
            .nombre(command.getNombre())
            .descripcion(command.getDescripcion())
            .build();

        Role roleUpdated = this.roleRepository.save(role);
        roleUpdated.setEventPublisher(this.eventPublisher);
        roleUpdated.update(); // Publica el evento RoleUpdatedEvent

        return RoleDto.create(roleUpdated);
    }

    @Override
    public void deleteRoleById(DeleteRoleCommand command) {
        Role role =  this.roleRepository.findById(command.getId())
            .orElseThrow(()-> new RuntimeException("No se encontro el rol con el id: " + command.getId()));

        this.roleRepository.delete(command.getId());
        role.setEventPublisher(this.eventPublisher);
        role.delete(); // Publica el evento RoleDeletedEvent
    }
}
