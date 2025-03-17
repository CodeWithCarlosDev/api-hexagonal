package red.social.interesescomunes.user.domain.event;

import red.social.interesescomunes.user.domain.model.User;

public class UserDeletedEvent {
    private User user;

    public UserDeletedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
