package red.social.interesescomunes.user.domain.event;

import red.social.interesescomunes.user.domain.model.User;

public class UserCreatedEvent {
    private User user;

    public UserCreatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
