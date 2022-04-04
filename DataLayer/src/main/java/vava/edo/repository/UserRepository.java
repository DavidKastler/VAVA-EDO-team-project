package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
