package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
