package red.social.interesescomunes.user.application.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;
import red.social.interesescomunes.user.infrastructure.api.dto.UserDto;

import java.util.List;

@Getter
@Setter
@Builder
public class CreateUserCommand {
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String clave;
    private List<RoleDto> roles;

    public static CreateUserCommand create(UserDto user){

        return CreateUserCommand.builder()
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .direccion(user.getDireccion())
                .correo(user.getCorreo())
                .clave(user.getClave())
                .roles(user.getRoles())
                .build();
    }
}
