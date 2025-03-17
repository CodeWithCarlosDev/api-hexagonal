package red.social.interesescomunes.user.infrastructure.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;
import red.social.interesescomunes.role.infrastructure.persistence.mysql.jpa.IRoleJpaRepository;
import red.social.interesescomunes.role.infrastructure.persistence.mysql.jpa.RoleEntity;
import red.social.interesescomunes.role.infrastructure.persistence.mysql.jpa.RoleRepositoryAdapter;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.domain.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryAdapter implements IUserRepository {

    private final IUserJpaRepository  repository;

    public UserRepositoryAdapter(IUserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
    List<UserEntity> usersEntities = (List<UserEntity>) this.repository.findAll();
        return usersEntities.stream()
             .map(this::toDomain)
             .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.repository.findById(id).map(this::toDomain);
    }

    @Override
    public User save(User user) {
    UserEntity userEntity = this.repository.save(this.toEntity(user));
        return this.toDomain(userEntity);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public User toDomain(UserEntity userEntity){
        List<Role> roles = userEntity.getRoles().stream()
            .map( RoleRepositoryAdapter::toDomain )
            .toList();

         return  User.builder()
            .id(userEntity.getId())
            .nombre(userEntity.getNombre())
            .apellido(userEntity.getApellido())
            .direccion(userEntity.getDireccion())
            .correo(userEntity.getCorreo())
            .clave(userEntity.getClave())
            .roles(roles)
            .build();
    }

    public UserEntity toEntity(User user){

        List<RoleEntity> roleEntities =  user.getRoles().stream()
            .map(RoleRepositoryAdapter::toEntity)
            .toList();

        return  UserEntity.builder()
            .id(user.getId())
            .nombre(user.getNombre())
            .apellido(user.getApellido())
            .direccion(user.getDireccion())
            .correo(user.getCorreo())
            .clave(user.getClave())
            .roles(roleEntities)
            .build();
    }
}
