package red.social.interesescomunes.role.application.query;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FindRoleByIdQuery {
    private Long id;
}
