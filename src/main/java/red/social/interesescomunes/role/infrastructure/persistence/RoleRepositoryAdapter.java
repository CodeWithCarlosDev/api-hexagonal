package red.social.interesescomunes.role.infrastructure.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.repository.IRoleRepository;

import java.util.List;
import java.util.Optional;

@Component
public class RoleRepositoryAdapter implements IRoleRepository {

    private final IRoleJpaRepository roleRepository;

    public RoleRepositoryAdapter(IRoleJpaRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> rolesEntities = (List<RoleEntity>) this.roleRepository.findAll();
        return rolesEntities.stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Role> findById(Long id) {
       return  this.roleRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Role save(Role role) {
         RoleEntity roleEntity = this.roleRepository.save( this.toEntity(role) );
         return this.toDomain(roleEntity);
    }

    @Override
    public void delete(Long id) {
        this.roleRepository.deleteById(id);
    }

    private Role toDomain(RoleEntity entity){
        return Role.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }

    private RoleEntity toEntity(Role role){
        return RoleEntity.builder()
                .id(role.getId())
                .nombre(role.getNombre())
                .descripcion(role.getDescripcion())
                .build();
    }
}
