package red.social.interesescomunes.role.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.role.application.command.CreateIRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRolCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.application.query.FindRoleByIdQuery;
import red.social.interesescomunes.role.application.service.IRoleService;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private IRoleService roleService;

    public RoleController(IRoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RoleResponse> findRoleById(@PathVariable Long id){
        RoleResponse roleResponse = this.roleService.findRoleById(new FindRoleByIdQuery(id))
                .map( role -> new RoleResponse(role.getId(), role.getNombre(), role.getDescripcion()))
                .orElseThrow();
        return  ResponseEntity.ok(roleResponse);
    }

    @GetMapping("/find-all")
    public  ResponseEntity<List<RoleResponse>> findAllRoles(){
        List<RoleResponse>  roles = this.roleService.findAllRoles()
                .stream()
                .map( role -> new RoleResponse(role.getId(), role.getNombre(), role.getDescripcion()))
                .toList();
        return  ResponseEntity.ok(roles);
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponse> createRole(@RequestBody CreateIRoleCommand command){
        RoleResponse createdRole = this.roleService.createRole(command);
        RoleResponse response = RoleResponse.builder()
            .id(createdRole.getId())
            .nombre(createdRole.getNombre())
            .descripcion(createdRole.getDescripcion())
            .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @RequestBody UpdateRoleCommand command){
        RoleResponse role = this.roleService.findRoleById(new FindRoleByIdQuery(id)).orElseThrow();
        command.setId(role.getId());
        RoleResponse roleUpdate = this.roleService.updateRole(command);
        return ResponseEntity.ok(roleUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleRoleById(@PathVariable Long id) {
        this.roleService.deleteRoleById(new DeleteRolCommand(id));
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente");
    }

}
