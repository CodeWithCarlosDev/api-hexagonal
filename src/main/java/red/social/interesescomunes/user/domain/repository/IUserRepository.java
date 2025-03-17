package red.social.interesescomunes.user.domain.repository;

import red.social.interesescomunes.user.domain.model.User;
import java.util.List;
import java.util.Optional;

// definimos las operaciones que puede realizar el usuario en la base de datos.
public interface IUserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void delete(Long id);
}
