package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Chat;
import vava.edo.model.Task;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    List<Chat> findAllBySenderId(Integer senderId);
}