package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Relationship;
import vava.edo.model.User;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

    Relationship findByFirstUserIdAndSecondUserId(User sender, User receiver);
    Relationship findByFirstUserIdAndSecondUserIdAndStatus(User sender, User receiver, String status);
    List<Relationship> findAllByFirstUserIdAndStatus(User user, String status);
    Boolean existsByFirstUserIdAndAndSecondUserIdAndAndStatus(User sender, User receiver, String status);
}
