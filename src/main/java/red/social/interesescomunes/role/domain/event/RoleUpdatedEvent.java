package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;

public class RoleUpdatedEvent {
    private final Role role;

    public RoleUpdatedEvent(Role role){
        this.role = role;
    }

    public Role getRole(){
        return role;
    }
}
