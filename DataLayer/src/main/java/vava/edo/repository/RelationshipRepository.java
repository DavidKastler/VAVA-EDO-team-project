package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Relationship;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

    @Query("select r from Relationship r where r.firstUser.uId = ?1 and r.secondUser.uId = ?2")
    Relationship findByFirstUserUIdAndSecondUserUId(Integer sender, Integer receiver);

    @Query("select r from Relationship r where r.secondUser.uId = ?1 and r.status = 'PENDING'")
    List<Relationship> findAllByUserUIdAndStatusIsPending(Integer userId);

    @Query("select r from Relationship r where (r.firstUser.uId = ?1 or r.secondUser.uId = ?1) and r.status = 'ACCEPTED'")
    List<Relationship> findAllByUserIdAndStatusIsAccepted(Integer user);

    @Query("select (count(r) > 0) from Relationship r where r.firstUser.uId = ?1 and r.secondUser.uId = ?2 and r.status = 'BLOCKED'")
    Boolean existsByFirstUserUIdAndSecondUserUIdAndStatusIsBlocked(Integer senderId, Integer receiverId);
}
