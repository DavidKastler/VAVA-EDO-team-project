package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Friendship;
import vava.edo.model.Relationships;
import vava.edo.model.User;

import java.util.List;

public interface RelationshipsRepository extends JpaRepository<Relationships, Integer> {
    List<Relationships> findAllByFirstUserIdAndStatus(User user, String status);
    Relationships findByFirstUserIdAndSecondUserIdAndStatus(User sender, User receiver, String status);
}
