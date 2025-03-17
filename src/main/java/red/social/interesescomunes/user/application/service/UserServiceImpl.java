package red.social.interesescomunes.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;
import red.social.interesescomunes.user.application.command.CreateUserCommand;
import red.social.interesescomunes.user.application.command.DeleteUserCommand;
import red.social.interesescomunes.user.application.command.UpdateUserCommand;
import red.social.interesescomunes.user.application.query.FindUserByIdQuery;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.domain.repository.IUserRepository;
import red.social.interesescomunes.user.infrastructure.api.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Service
// Servicio que procesa los casos de uso del usuario en la aplicacion
public class UserServiceImpl implements  IUserService {

    private IUserRepository userRepository;
    private ApplicationEventPublisher eventPublisher;

    public UserServiceImpl(ApplicationEventPublisher eventPublisher, IUserRepository userRepository) {
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        List<User> users = this.userRepository.findAll();

        if(users.isEmpty()){
            return Optional.empty();
        }

        List<UserDto> userDtos = users.stream()
            .map(UserDto::create)
            .toList();

        return  Optional.of(userDtos);
    }

    @Override
    public Optional<UserDto> findById(FindUserByIdQuery query) {
        UserDto user = this.userRepository.findById(query.getId())
                .map(UserDto::create)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario con el id: "+ query.getId()));

        return Optional.of(user);
    }

    @Override
    public UserDto create(CreateUserCommand command) {
        List<Role> roles = convertRolesDtoListToRoles(command.getRoles());
        User user = User.builder()
                .nombre(command.getNombre())
                .apellido(command.getApellido())
                .direccion(command.getDireccion())
                .correo(command.getCorreo())
                .clave(command.getClave())
                .roles(roles)
                .build();

        User userCreated = this.userRepository.save(user);
        userCreated.setEventPublisher(this.eventPublisher);
        userCreated.create();

        return UserDto.create(userCreated);
    }

    @Override
    public UserDto update(UpdateUserCommand command) {
        List<Role> roles = convertRolesDtoListToRoles(command.getRoles());
        User user = User.builder()
                .id(command.getId())
                .nombre(command.getNombre())
                .apellido(command.getApellido())
                .direccion(command.getDireccion())
                .correo(command.getCorreo())
                .clave(command.getClave())
                .roles(roles)
                .build();

        User userUpdated = this.userRepository.save(user);
        userUpdated.setEventPublisher(this.eventPublisher);
        userUpdated.update();

        return UserDto.create(userUpdated);
    }

    @Override
    public void delete(DeleteUserCommand command) {
        User user = this.userRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("No se encontro el usuario con el id: "+ command.getId()));

        user.setEventPublisher(this.eventPublisher);
        user.delete();
        this.userRepository.delete(command.getId());
    }

    public List<Role> convertRolesDtoListToRoles(List<RoleDto> rolesDto){
        return rolesDto.stream()
            .map(roleDto -> Role.builder()
                    .id(roleDto.getId())
                    .nombre(roleDto.getNombre())
                    .descripcion(roleDto.getDescripcion())
                    .build())
            .toList();
    }
}
