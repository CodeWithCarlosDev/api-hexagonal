package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;

public class RoleDeletedEvent {
    private final Role role;

    public RoleDeletedEvent(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
