package red.social.interesescomunes.role.infrastructure.persistence.mysql.jpa;

import org.springframework.data.repository.CrudRepository;

public interface IRoleJpaRepository extends CrudRepository<RoleEntity,Long> {
}
