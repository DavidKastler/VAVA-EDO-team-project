package vava.edo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Relationship;
import vava.edo.model.enums.RelationshipStatus;
import vava.edo.schema.relationships.RelationshipCreate;
import vava.edo.schema.relationships.RelationshipRequest;
import vava.edo.service.RelationshipService;
import vava.edo.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * Class that provides endpoints for relationship operations
 */
@Log4j2
@RestController
@RequestMapping("/relationships")
public class RelationshipController {

    private final RelationshipService relationshipService;
    private final UserService userService;

    @Autowired
    public RelationshipController(RelationshipService relationshipService, UserService userService) {
        this.relationshipService = relationshipService;
        this.userService = userService;
    }

    /**
     * Endpoint returning new friend request
     *
     * @param token user account rights verification
     * @return friend request
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewFriendRequest(@RequestParam(value = "token") Integer token,
                                                         @RequestBody RelationshipCreate relationshipDto) {
        // verification if user is in database
        userService.getUser(token);
        log.info("Creating new friend request from user {} to user {} ",
                relationshipDto.getSenderId(), relationshipDto.getReceiverName());
        if (!Objects.equals(token, relationshipDto.getSenderId())) {
            log.warn("Permission denied, cannot change friend list of other users.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cant create relationships for someone else.");
        }

        Integer senderId = relationshipDto.getSenderId();
        Integer receiverId = userService.getUserByUserName(relationshipDto.getReceiverName()).getUId();

        if (Objects.equals(senderId, receiverId)) {
            log.warn("Cannot send friend request to yourself.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First and second user cant be same.");
        }

        Relationship existingRequest = relationshipService.getRelationshipBySenderIdAndReceiverId(senderId, receiverId);
        if (existingRequest != null) {
            RelationshipStatus status = existingRequest.getStatus();
            if (status == RelationshipStatus.ACCEPTED) {
                log.info("User {} and {} are already friends.", senderId, receiverId);
                throw new ResponseStatusException(HttpStatus.OK, "Relationship already exists.");
            } else if (status == RelationshipStatus.PENDING) {
                log.info("Request already exists, just wait for answer.");
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Relationship is already pending.");
            } else if (status == RelationshipStatus.BLOCKED) {
                log.info("User {} has blocked you. Please respect others and do not bothered them.", receiverId);
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User has blocked you.");
            }
        }
        log.info("Friend request successfully created.");
        return new ResponseEntity<>(relationshipService.createRelationship(senderId, receiverId), HttpStatus.CREATED);
    }

    /**
     * Endpoint returning new accepted friend request
     *
     * @param token          user account rights verification
     * @param relationshipId user who accepted request
     * @return accepted friend request
     */
    @PutMapping(value = "/accept/{rsId}")
    public ResponseEntity<Relationship> acceptRequest(@RequestParam(value = "token") Integer token,
                                                      @PathVariable("rsId") Integer relationshipId) {
        // verification if user is in database
        userService.getUser(token);
        log.info("Accepting friend request.");
        Relationship relationship = relationshipService.getRelationship(relationshipId);
        if (!Objects.equals(relationship.getSecondUser().getUId(), token)) {
            log.warn("Cannot accept friends requests for other users.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Relationship is not yours.");
        }
        Relationship otherWayRelationship = relationshipService.getRelationshipBySenderIdAndReceiverId(
                relationship.getSecondUser().getUId(), relationship.getFirstUser().getUId());
        if (otherWayRelationship != null) {
            relationshipService.deleteRelationship(otherWayRelationship.getRelationshipId());
        }
        log.info("Request form {} to user {} accepted.",
                relationship.getFirstUser().getUsername(), relationship.getSecondUser().getUsername());
        return new ResponseEntity<>(relationshipService.acceptRelationshipRequest(relationshipId), HttpStatus.OK);
    }

    /**
     * Endpoint returning blocking friends
     *
     * @param token          user account rights verification
     * @param relationshipId user who will be blocked
     * @return blocked user
     */
    @PutMapping(value = "/block/{rsId}")
    public ResponseEntity<Relationship> blockUser(@RequestParam(value = "token") Integer token,
                                                  @PathVariable("rsId") Integer relationshipId) {
        // verification if user is in database
        userService.getUser(token);
        log.info("Blocking user.");
        Relationship relationship = relationshipService.getRelationship(relationshipId);
        if (!Objects.equals(relationship.getSecondUser().getUId(), token)) {
            log.warn("Cannot block for other users.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Relationship is not yours.");
        }
        Relationship otherWayRelationship = relationshipService.getRelationshipBySenderIdAndReceiverId(
                relationship.getSecondUser().getUId(), relationship.getFirstUser().getUId());
        if (otherWayRelationship != null) {
            relationshipService.deleteRelationship(otherWayRelationship.getRelationshipId());
        }
        log.info("User {} was blocked.", relationship.getSecondUser().getUsername());
        return new ResponseEntity<>(relationshipService.blockRelationshipRequest(relationshipId), HttpStatus.OK);
    }

    /**
     * Endpoint for rejecting friend request, rejected requests will be removed from database
     *
     * @param token          user account rights verification
     * @param relationshipId relationshipId who reject request
     * @return rejected friend request
     */
    @DeleteMapping(value = "/delete/{rsId}")
    public ResponseEntity<Object> rejectRequest(@RequestParam(value = "token") int token,
                                                @PathVariable("rsId") Integer relationshipId) {
        // verification if user is in database
        userService.getUser(token);
        log.info("Rejecting friend request.");
        Relationship relationship = relationshipService.getRelationship(relationshipId);
        if (!Objects.equals(relationship.getSecondUser().getUId(), token) &&
                !Objects.equals(relationship.getFirstUser().getUId(), token)) {
            log.warn("Cannot reject requests for other users");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Relationship is not yours.");
        }
        log.info("Request from user {} was rejected.", relationship.getFirstUser().getUsername());
        return new ResponseEntity<>(relationshipService.deleteRelationship(relationshipId), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of friends
     *
     * @param token user account rights verification
     * @return list of friends {"username}
     */
    @GetMapping(value = "/friends")
    public ResponseEntity<List<RelationshipRequest>> getAllFriends(@RequestParam(value = "token") Integer token) {
        // verification if user is in database
        userService.getUser(token);
        log.info("Get list of all friends.");
        return new ResponseEntity<>(relationshipService.getAllFriends(token), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of friend requests
     *
     * @param token user account rights verification
     * @return list of friend requests
     */
    @GetMapping(value = "/requests")
    public ResponseEntity<List<RelationshipRequest>> getAllFriendRequests(@RequestParam(value = "token") Integer token) {
        // verification if user is in database
        userService.getUser(token);
        log.info("Get list of all friend requests.");
        return new ResponseEntity<>(relationshipService.getAllPendingRequests(token), HttpStatus.OK);
    }
}
