package red.social.interesescomunes.user.application.query;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import red.social.interesescomunes.user.application.command.DeleteUserCommand;
import red.social.interesescomunes.user.infrastructure.api.dto.UserDto;

@Getter
@Setter
@Builder
public class FindUserByIdQuery {
    private Long id;

    public static FindUserByIdQuery create(Long idUser){
        return  FindUserByIdQuery.builder().id(idUser).build();
    }
}
