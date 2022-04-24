package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vava.edo.model.exeption.UserNotFoundException;
import vava.edo.model.Role;
import vava.edo.repository.RoleRepository;
/**
 * Class that provides endpoints for role operations
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Endpoint that gets info on role by its id
     * @param roleId    role id
     * @return          found role
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") int roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new UserNotFoundException(roleId));
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
