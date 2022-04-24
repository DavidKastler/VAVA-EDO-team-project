package vava.edo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Relationship;
import vava.edo.model.User;
import vava.edo.model.enums.RelationshipStatus;
import vava.edo.repository.RelationshipRepository;
import vava.edo.schema.relationships.RelationshipRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that operates over 'relationships' database table
 */
@Service
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;
    private final UserService userService;


    public RelationshipService(RelationshipRepository relationshipRepository, UserService userService) {
        this.relationshipRepository = relationshipRepository;
        this.userService = userService;
    }

    /**
     * Method that returns all relationships
     * @return list of all relationships
     */
    public List<Relationship> getAllRelationships() {
        return relationshipRepository.findAll();
    }

    /**
     * Method finding relationship in database by its primary key
     * @param relationshipId    relationship id
     * @return                  found relationship
     */
    public Relationship getRelationship(Integer relationshipId) {
        return relationshipRepository.findById(relationshipId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship not found")
        );
    }

    /**
     * Method returns relation between users
     * @param senderId   user who send request
     * @param receiverId user who received request
     * @return          relationship between users
     */
    public Relationship getRelationshipBySenderIdAndReceiverId(Integer senderId, Integer receiverId) {
        return relationshipRepository.findByFirstUserUIdAndSecondUserUId(senderId, receiverId);
    }

    /**
     * Method that creates new relationships and saves it to database
     * @param senderId   user id who send request
     * @param receiverId user id who received request
     * @return created relationships
     */
    public Relationship createRelationship(Integer senderId, Integer receiverId) {
        User sender = userService.getUser(senderId);
        User receiver = userService.getUser(receiverId);
        Relationship newRequest = new Relationship(sender, receiver);

        return relationshipRepository.save(newRequest);
    }

    /**
     * Method returns all user's friends in database
     * @param userId    given user id
     * @return          list of users friends
     */
    public List<RelationshipRequest> getAllFriends(Integer userId) {
        List<Relationship> friendList = relationshipRepository.findAllByUserIdAndStatusIsAccepted(userId);
        List<RelationshipRequest> requests = new ArrayList<>();

        for (Relationship relationship : friendList) {
            requests.add(RelationshipRequest.from(relationship));
        }
        return requests;
    }

    /**
     * Method returns all user's friend requests in database
     * @param   userId given user id
     * @return  list of user's friend requests
     */
    public List<RelationshipRequest> getAllPendingRequests(Integer userId) {
        List<Relationship> pendingList = relationshipRepository.findAllByUserUIdAndStatusIsPending(userId);
        List<RelationshipRequest> requests = new ArrayList<>();

        for (Relationship relationship : pendingList) {
            requests.add(RelationshipRequest.from(relationship));
        }
        return requests;
    }

    /**
     * Method to update status of relationship to accepted
     * @param relationshipId    relationship id
     * @return                  updated relationship
     */
    @Transactional
    public Relationship acceptRelationshipRequest(Integer relationshipId) {
        Relationship relationship = getRelationship(relationshipId);

        relationship.setStatus(RelationshipStatus.ACCEPTED);
        return relationship;
    }

    /**
     * Method to update status of relationship to block
     * @param relationshipId    relationship id
     * @return                  updated relationship
     */
    @Transactional
    public Relationship blockRelationshipRequest(Integer relationshipId) {
        Relationship relationship = getRelationship(relationshipId);

        relationship.setStatus(RelationshipStatus.BLOCKED);
        return relationship;
    }

    /**
     * Method to update status of relationship to reject/ delete
     * @param relationshipId    relationship id
     * @return                  deleted relationship
     */
    public Relationship deleteRelationship(Integer relationshipId) {
        Relationship relationship = getRelationship(relationshipId);

        relationshipRepository.delete(relationship);
        return relationship;
    }
}
