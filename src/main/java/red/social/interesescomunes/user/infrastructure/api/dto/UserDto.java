package red.social.interesescomunes.user.infrastructure.api.dto;

import lombok.*;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;
import red.social.interesescomunes.user.domain.model.User;

import java.util.List;



@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String clave;
    private List<RoleDto> roles;

    public static UserDto create(User user){
        List<RoleDto> roles = user.getRoles()
            .stream()
            .map(RoleDto::create)
            .toList();

        return  UserDto.builder()
            .id(user.getId())
            .nombre(user.getNombre())
            .apellido(user.getApellido())
            .direccion(user.getDireccion())
            .correo(user.getCorreo())
            .clave(user.getClave())
            .roles(roles)
            .build();
    }
}
