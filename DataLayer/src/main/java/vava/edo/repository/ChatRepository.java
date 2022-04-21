package vava.edo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    List<Chat> findAllByGroupIdOrderByTimeSent(Integer groupId, Pageable pageable);
}
