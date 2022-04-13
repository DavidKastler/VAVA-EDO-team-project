package vava.edo.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vava.edo.model.Relationships;
import vava.edo.model.User;
import vava.edo.repository.RelationshipsRepository;
import vava.edo.schema.RelationshipCreate;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelationshipsService {

    private final RelationshipsRepository relationshipsRepository;

    public RelationshipsService(RelationshipsRepository relationshipsRepository) {
        this.relationshipsRepository = relationshipsRepository;
    }


    /**
     * Method that returns all relationships
     * @return list of all relationships
     */
    public List<Relationships> getAllRelationships() {return relationshipsRepository.findAll();}


    /**
     * Method that creates new relationships and saves it to database
     * @param sender    user who send request
     * @param receiver  user who received request
     * @return          created relationships
     */
    public Relationships addRequest(User sender, User receiver) {
        RelationshipCreate createNewRequest = new RelationshipCreate(sender, receiver);
        Relationships newRequest = Relationships.from(createNewRequest);

        relationshipsRepository.save(newRequest);
        return newRequest;
    }


    /**
     * Method returns all user's friends in database
     * @param user  given user
     * @return      list of users friends
     */
    public List<java.util.Map.Entry<String , Integer>> getAllFriends(User user) {
        List<Relationships> friendList = relationshipsRepository.findAllByFirstUserIdAndStatus(user, "accepted");
        List<java.util.Map.Entry<String , Integer>> users = new ArrayList<>();
        for (Relationships r: friendList) {
            users.add(new AbstractMap.SimpleEntry<>(r.getSecondUserId().getUsername(), r.getSecondUserId().getUId()));
        }

        return users;
    }


    /**
     * Method returns all user's friend requests in database
     * @param user  given user
     * @return      list of user's friend requests
     */
    public List<User> getAllFriendRequests(User user) {
        List<Relationships> pendingList = relationshipsRepository.findAllByFirstUserIdAndStatus(user, "pending");
        List<User> pendingUsers = new ArrayList<>();
        for (Relationships r: pendingList) {
            pendingUsers.add(r.getSecondUserId());
        }
        return pendingUsers;
    }


    /**
     * Method for rejecting friend requests
     * @param sender    user who send request
     * @param receiver  user who received request
     * @return          rejected friend request
     */
    public Relationships rejectRequest(User sender, User receiver) {
        Relationships request = relationshipsRepository.findByFirstUserIdAndSecondUserIdAndStatus
                (sender, receiver, "pending");
        relationshipsRepository.delete(request);

        return request;
    }


    /**
     * Method for accepting friend requests
     * @param sender    user who send request
     * @param receiver  user who received request
     * @return          accepted friend request
     */
    @Transactional
    public Relationships acceptRequest(User sender, User receiver) {
        Relationships request = relationshipsRepository.findByFirstUserIdAndSecondUserIdAndStatus
                (sender, receiver, "pending");
        if (relationshipsRepository.findByFirstUserIdAndSecondUserIdAndStatus
                (sender, receiver, "accepted") != null) {
            rejectRequest(sender, receiver);
            return new Relationships();
        }
        request.setStatus("accepted");
        request.setSince(new java.sql.Date(System.currentTimeMillis()));

        return request;
    }



}
