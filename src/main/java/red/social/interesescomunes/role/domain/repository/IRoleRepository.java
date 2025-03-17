package red.social.interesescomunes.role.domain.repository;

import red.social.interesescomunes.role.domain.model.Role;

import java.util.List;
import java.util.Optional;

// definimos las operaciones que puede realizar el rol en la base de datos.
public interface IRoleRepository {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void delete(Long id);
}
