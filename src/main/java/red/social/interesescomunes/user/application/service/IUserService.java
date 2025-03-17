package red.social.interesescomunes.user.application.service;

import red.social.interesescomunes.user.application.command.CreateUserCommand;
import red.social.interesescomunes.user.application.command.DeleteUserCommand;
import red.social.interesescomunes.user.application.command.UpdateUserCommand;
import red.social.interesescomunes.user.application.query.FindUserByIdQuery;
import red.social.interesescomunes.user.infrastructure.api.dto.UserDto;

import java.util.List;
import java.util.Optional;

// interfaz que define los casos de uso del usuario en la aplicacion
public interface IUserService {
   Optional<List<UserDto>> findAll();
   Optional<UserDto> findById(FindUserByIdQuery query);
   UserDto create(CreateUserCommand command);
   UserDto update(UpdateUserCommand command);
   void delete(DeleteUserCommand command);
}
