package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;


public class RoleCreatedEvent {
    private final Role role;

    public RoleCreatedEvent(Role role){
        this.role = role;
    }

    public Role getRole(){
        return this.role;
    }

}
