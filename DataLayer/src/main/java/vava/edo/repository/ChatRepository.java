package vava.edo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where c.groupId = ?1 order by c.timeSent DESC")
    List<Chat> findAllByGroupIdOrderByTimeSentDesc(Integer groupId, Pageable pageable);
}
