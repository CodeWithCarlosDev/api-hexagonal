package red.social.interesescomunes.user.domain.model;


import lombok.*;
import org.springframework.context.ApplicationEventPublisher;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.user.domain.event.UserCreatedEvent;
import red.social.interesescomunes.user.domain.event.UserDeletedEvent;
import red.social.interesescomunes.user.domain.event.UserUpdatedEvent;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String clave;
    private List<Role> roles;
    private ApplicationEventPublisher eventPublisher;

    public void create(){
        this.eventPublisher.publishEvent(new UserCreatedEvent(this));
    }

    public void update(){
        this.eventPublisher.publishEvent(new UserUpdatedEvent(this));
    }

    public void delete(){
        this.eventPublisher.publishEvent(new UserDeletedEvent(this));
    }

}
