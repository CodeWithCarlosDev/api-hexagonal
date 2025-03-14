package red.social.interesescomunes.role.application.command;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateIRoleCommand  {
    private String nombre;
    private String descripcion;
}
