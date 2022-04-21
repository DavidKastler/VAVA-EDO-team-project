package vava.edo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Relationship;
import vava.edo.model.User;
import vava.edo.model.enums.RelationshipStatus;
import vava.edo.repository.RelationshipRepository;
import vava.edo.schema.RelationshipEditStatus;
import vava.edo.schema.users.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that operates over 'relationships' database table
 */
@Service
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;

    public RelationshipService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }


    /**
     * Method that returns all relationships
     *
     * @return list of all relationships
     */
    public List<Relationship> getAllRelationships() {
        return relationshipRepository.findAll();
    }

    /**
     * Method returns relation between users
     *
     * @param sender   user who send request
     * @param receiver user who received request
     * @return relationship between users
     */
    public Relationship getRelation(User sender, User receiver) {
        return relationshipRepository.findByFirstUserIdAndSecondUserId(sender, receiver);
    }


    /**
     * Method that creates new relationships and saves it to database
     *
     * @param sender   user who send request
     * @param receiver user who received request
     * @param status   status which should be added
     * @return created relationships
     */
    public Relationship addRelation(User sender, User receiver, RelationshipStatus status) {
        RelationshipEditStatus createNewRequest = new RelationshipEditStatus(sender, receiver, status);
        Relationship newRequest = Relationship.from(createNewRequest);

        relationshipRepository.save(newRequest);
        return newRequest;
    }


    /**
     * Method that update new relationships and saves it to database
     *
     * @param sender   user who send request
     * @param receiver user who received request
     * @param status   updated status
     * @return created relationships
     */
    @Transactional
    public Relationship updateRelationshipStatus(User sender, User receiver, String status) {
        Relationship updatedRequest = relationshipRepository.findByFirstUserIdAndSecondUserId(sender, receiver);
        updatedRequest.setStatus(RelationshipStatus.accepted);
        updatedRequest.setSince(System.currentTimeMillis() / 1000L);

        return updatedRequest;
    }


    /**
     * Method returns all user's friends in database
     *
     * @param user given user
     * @return list of users friends
     */
    public List<UserInfo> getAllFriends(User user) {
        List<Relationship> friendList = relationshipRepository.findAllByFirstUserIdAndStatus(user, "accepted");
        List<UserInfo> users = new ArrayList<>();
        for (Relationship r : friendList) {
            users.add(UserInfo.from(r.getSecondUserId()));
        }

        return users;
    }


    /**
     * Method returns all user's friend requests in database
     *
     * @param user given user
     * @return list of user's friend requests
     */
    public List<User> getAllFriendRequests(User user) {
        List<Relationship> pendingList = relationshipRepository.findAllByFirstUserIdAndStatus(user, "pending");
        List<User> pendingUsers = new ArrayList<>();
        for (Relationship r : pendingList) {
            pendingUsers.add(r.getSecondUserId());
        }
        return pendingUsers;
    }


    /**
     * Method for deleting accepted or blocked friend requests and also rejecting/deleting pending requests
     *
     * @param sender   user who send request
     * @param receiver user who received request
     */
    public void endRelation(User sender, User receiver) {
        Relationship request = relationshipRepository.
                findByFirstUserIdAndSecondUserId(sender, receiver);
        if (request != null) {
            relationshipRepository.delete(request);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is nothing to delete.");
        }
    }


    /**
     * Method for rejecting pending requests, rejected request will delete it from database
     *
     * @param sender   user who send request
     * @param receiver user who received request
     */
    public void rejectRequest(User sender, User receiver) {
        if (relationshipRepository.
                existsByFirstUserIdAndAndSecondUserIdAndAndStatus(sender, receiver, "pending")) {
            relationshipRepository.delete(relationshipRepository.findByFirstUserIdAndSecondUserId(sender, receiver));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is nothing to delete.");
        }
    }


    /**
     * Method for accepting friend requests
     *
     * @param sender   user who send request
     * @param receiver user who received request
     * @return accepted friend request
     */
    @Transactional
    public Relationship acceptRequest(User sender, User receiver) {
        Relationship request = relationshipRepository.
                findByFirstUserIdAndSecondUserIdAndStatus(sender, receiver, "pending");
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is nothing to accept.");
        }
        request.setStatus(RelationshipStatus.accepted);
        request.setSince(System.currentTimeMillis() / 1000L);
        Relationship receiverRequest = relationshipRepository.
                findByFirstUserIdAndSecondUserId(receiver, sender);
        // Request exists
        if (receiverRequest != null) {
            receiverRequest.setStatus(RelationshipStatus.accepted);
            receiverRequest.setSince(System.currentTimeMillis() / 1000L);
        } else {
            receiverRequest = new Relationship(receiver, sender, RelationshipStatus.accepted);
            relationshipRepository.save(receiverRequest);
        }

        return request;
    }


    /**
     * Method for accepting users
     *
     * @param sender   user who send request
     * @param receiver user who received request
     * @return blocked user
     */
    @Transactional
    public Relationship blockUser(User sender, User receiver) {
        Relationship request = relationshipRepository.
                findByFirstUserIdAndSecondUserIdAndStatus(sender, receiver, "accepted");
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is nothing to blocked.");
        }
        request.setStatus(RelationshipStatus.blocked);
        request.setSince(System.currentTimeMillis() / 1000L);
        endRelation(receiver, sender);

        return request;
    }


    /**
     * Method for checking the status between given users if exists
     *
     * @param sender   user who send request
     * @param receiver user who received request
     * @param status   wanted status
     * @return true if relation exists, and it matches with given statues else false
     */
    public Boolean checkFriendshipStatus(User sender, User receiver, String status) {
        return relationshipRepository.
                existsByFirstUserIdAndAndSecondUserIdAndAndStatus(sender, receiver, status);
    }


}
