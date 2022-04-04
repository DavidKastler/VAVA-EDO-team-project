package vava.edo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vava.edo.exeption.Error404NotFoundException;
import vava.edo.exeption.RoleNotFoundException;
import vava.edo.exeption.UserNotFoundException;
import vava.edo.model.Role;
import vava.edo.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    /**
     * Method finds role by its ID, if it is not found throws exception
     * @param roleId    role ID you want to find
     * @return          found role
     */
    public Role getRole(int roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new RoleNotFoundException(roleId));
    }

}
