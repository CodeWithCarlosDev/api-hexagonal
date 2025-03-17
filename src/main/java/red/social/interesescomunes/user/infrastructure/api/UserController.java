package red.social.interesescomunes.user.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.user.application.command.CreateUserCommand;
import red.social.interesescomunes.user.application.command.DeleteUserCommand;
import red.social.interesescomunes.user.application.command.UpdateUserCommand;
import red.social.interesescomunes.user.application.query.FindUserByIdQuery;
import red.social.interesescomunes.user.application.service.IUserService;
import red.social.interesescomunes.user.infrastructure.api.dto.UserDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
// Se encarga de procesar todas las peticiones del cliente con respecto al usuario
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        Optional<List<UserDto>> userDtos = this.service.findAll();
        return userDtos.isEmpty()
            ? ResponseEntity.ok("No hay usuarios en el sistema.")
            : ResponseEntity.ok(userDtos.get());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        Optional<UserDto> optionalUserDto = this.service.findById(FindUserByIdQuery.create(id));
        UserDto userDto = optionalUserDto.orElseThrow();
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userRequest) {
        System.out.println("userRequest:" + userRequest);
        CreateUserCommand command = CreateUserCommand.create(userRequest);
        UserDto userCreated = this.service.create(command);
        return ResponseEntity.ok(userCreated);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userRequest) {
        Optional<UserDto> optionalUserDto = this.service.findById(FindUserByIdQuery.create(id));
        UserDto userDto =  optionalUserDto.orElseThrow();

        userRequest.setId(userDto.getId());
        UpdateUserCommand command =  UpdateUserCommand.create(userRequest);
        UserDto userUpdated = this.service.update(command);

        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id) {
        this.service.delete(DeleteUserCommand.create(id));
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el usuario con id: " + id);
    }
}
