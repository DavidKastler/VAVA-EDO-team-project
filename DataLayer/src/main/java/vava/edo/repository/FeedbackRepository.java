package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
