package red.social.interesescomunes.role.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

public interface IRoleJpaRepository extends CrudRepository<RoleEntity,Long> {
}
