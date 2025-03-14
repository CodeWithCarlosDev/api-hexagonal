package red.social.interesescomunes.role.application.command;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRoleCommand  {
    private Long id;
    private String nombre;
    private String descripcion;
}
