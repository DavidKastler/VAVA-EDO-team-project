package vava.edo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where c.groupId = ?1 order by c.timeSent DESC")
    List<Chat> findAllByGroupIdOrderByTimeSentDesc(Integer groupId, Pageable pageable);

    @Query(value = "SELECT g.group_name AS groupId, MAX(time_sent) AS lastSentMessage " +
                   "FROM chat c " +
                   "INNER JOIN groups g ON g.gr_id = c.group_id " +
                   "INNER JOIN group_members gm ON gm.group_id = g.gr_id " +
                   "WHERE gm.member_id = ?1 " +
                   "GROUP BY group_name " +
                   "ORDER BY lastSentMessage DESC",
            nativeQuery = true)
    List<String> getRecentChatGroupsForUser(Integer userId);
}
