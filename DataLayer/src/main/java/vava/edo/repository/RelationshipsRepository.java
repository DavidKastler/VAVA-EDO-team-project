package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Relationships;
import vava.edo.model.User;

import java.util.List;

public interface RelationshipsRepository extends JpaRepository<Relationships, Integer> {

    Relationships findByFirstUserIdAndSecondUserIdAndStatus(User sender, User receiver, String status);
    List<Relationships> findAllByFirstUserIdAndStatus(User user, String status);
}
