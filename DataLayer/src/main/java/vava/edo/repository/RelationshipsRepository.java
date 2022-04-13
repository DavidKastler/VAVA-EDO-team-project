package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Relationships;

public interface RelationshipsRepository extends JpaRepository<Relationships, Integer> {
}
