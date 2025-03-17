package red.social.interesescomunes.role.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.role.domain.event.RoleCreatedEvent;
import red.social.interesescomunes.role.domain.event.RoleDeletedEvent;
import red.social.interesescomunes.role.domain.event.RoleUpdatedEvent;

@Component
public class RoleEventListener {

    @EventListener
    public void handleRoleCreated(RoleCreatedEvent event){
        System.out.println("Role creado : " + event.getRole().getNombre() + " ( " + event.getRole().getDescripcion()  + " )");
    }

    @EventListener
    public void handleRoleUpdated(RoleUpdatedEvent event){
        System.out.println("Role actualizado : " + event.getRole().getNombre() + " ( " + event.getRole().getDescripcion()  + " )");
    }

    @EventListener
    public void handleRoleDeleted(RoleDeletedEvent event){
        System.out.println("Role eliminado : " + event.getRole().getNombre() + " ( " + event.getRole().getDescripcion()  + " )");
    }

}
