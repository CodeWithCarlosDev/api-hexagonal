package red.social.interesescomunes.role.application.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteRolCommand {
    private Long id;
}
