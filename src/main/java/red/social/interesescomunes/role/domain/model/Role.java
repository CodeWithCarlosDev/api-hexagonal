package red.social.interesescomunes.role.domain.model;


import lombok.*;
import org.springframework.context.ApplicationEventPublisher;
import red.social.interesescomunes.role.domain.event.RoleCreatedEvent;
import red.social.interesescomunes.role.domain.event.RoleDeletedEvent;
import red.social.interesescomunes.role.domain.event.RoleUpdatedEvent;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private TypeRole nombre;
    private String descripcion;
    private ApplicationEventPublisher eventPublisher;

    // evento de crear un rol
    public void create(){
        this.eventPublisher.publishEvent( new RoleCreatedEvent(this));
    }

    // evento de actualizacion de un rol
    public void update(){
        this.eventPublisher.publishEvent(new RoleUpdatedEvent(this));
    }

    // evento de eliminacion de un rol
    public void delete(){
        this.eventPublisher.publishEvent(new RoleDeletedEvent(this));
    }

}
