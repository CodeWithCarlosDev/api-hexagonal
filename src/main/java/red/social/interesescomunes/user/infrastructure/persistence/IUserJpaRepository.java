package red.social.interesescomunes.user.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserJpaRepository extends CrudRepository<UserEntity,Long> {
}
