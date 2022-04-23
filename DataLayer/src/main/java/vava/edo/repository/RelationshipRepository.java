package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Relationship;
import vava.edo.model.User;
import vava.edo.model.enums.RelationshipStatus;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

    @Query("select r from Relationship r where r.firstUser.uId = ?1 and r.secondUser.uId = ?2")
    Relationship findByFirstUserUIdAndSecondUserUId(Integer sender, Integer receiver);

    @Query("select r from Relationship r where r.firstUser.uId = ?1 and r.status = 'pending'")
    List<Relationship> findAllByUserUIdAndStatusIsPending(Integer userId);

    @Query("select r from Relationship r where (r.firstUser.uId = ?1 or r.secondUser.uId = ?1) and r.status = 'accepted'")
    List<Relationship> findAllByUserIdAndStatusIsAccepted(Integer user);

    @Query("select (count(r) > 0) from Relationship r where r.firstUser.uId = ?1 and r.secondUser.uId = ?2 and r.status = 'blocked'")
    Boolean existsByFirstUserUIdAndSecondUserUIdAndStatusIsBlocked(Integer senderId, Integer receiverId);
}
