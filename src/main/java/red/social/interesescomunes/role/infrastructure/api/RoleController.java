package red.social.interesescomunes.role.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.role.application.command.CreateRoleCommand;
import red.social.interesescomunes.role.application.command.DeleteRoleCommand;
import red.social.interesescomunes.role.application.command.UpdateRoleCommand;
import red.social.interesescomunes.role.application.query.FindRoleByIdQuery;
import red.social.interesescomunes.role.application.service.IRoleService;
import red.social.interesescomunes.role.infrastructure.api.dto.RoleDto;
import red.social.interesescomunes.user.application.command.UpdateUserCommand;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
// Se encarga de procesar todas las peticiones del cliente con respecto al rol
public class RoleController {
    private IRoleService service;

    public RoleController(IRoleService service){
        this.service = service;
    }

    @GetMapping("/find-all")
    public  ResponseEntity<?> findAllRoles(){
        Optional<List<RoleDto>> roleDtos =  this.service.findAllRoles();

        return roleDtos.isEmpty()
            ? ResponseEntity.ok("No hay roles en el sistema.")
            : ResponseEntity.ok(roleDtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RoleDto> findRoleById(@PathVariable Long id){
        Optional<RoleDto> optionalRoleDto =   this.service.findRoleById(new FindRoleByIdQuery(id));
        RoleDto roleDto = optionalRoleDto.orElseThrow();
        return  ResponseEntity.ok(roleDto);
    }

    @PostMapping("/create")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto userRequest){
        CreateRoleCommand command = CreateRoleCommand.create(userRequest);
        RoleDto roleCreated = this.service.createRole(command);
        return ResponseEntity.ok(roleCreated);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleDto roleRequest){
        Optional<RoleDto> optionalRoleDto = this.service.findRoleById(new FindRoleByIdQuery(id));
        RoleDto roleDto = optionalRoleDto.orElseThrow();

        roleRequest.setId(roleDto.getId());
        UpdateRoleCommand command = UpdateRoleCommand.create(roleRequest);
        RoleDto roleUpdate = this.service.updateRole(command);

        return ResponseEntity.ok(roleUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleRoleById(@PathVariable Long id) {
        this.service.deleteRoleById(DeleteRoleCommand.create(id));
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el rol con id: " + id);
    }
}
