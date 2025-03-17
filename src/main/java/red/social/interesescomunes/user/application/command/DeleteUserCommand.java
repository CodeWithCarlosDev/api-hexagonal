package red.social.interesescomunes.user.application.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import red.social.interesescomunes.user.infrastructure.api.dto.UserDto;

@Getter
@Setter
@Builder
@ToString
public class DeleteUserCommand {
    private Long id;

    public static DeleteUserCommand create(Long IdUser){
        return  DeleteUserCommand.builder().id(IdUser).build();
    }
}
