package vava.edo.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Feedback;
import vava.edo.model.User;
import vava.edo.model.enums.FeedbackReadStatus;
import vava.edo.repository.FeedbackRepository;

import java.util.List;

/**
 * Service that operates over 'feedback' database table
 */
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public FeedbackService(FeedbackRepository feedbackRepository, UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
    }

    /**
     * Method returns feedback with given ID
     * @param feedbackId getting feedback ID
     * @return      feedback
     */
    public Feedback getFeedback(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback id not found."));
    }

    /**
     * Method returns all feedbacks in database
     * @return  list of all feedbacks
     */
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getAllUnseenFeedback() {
        return feedbackRepository.findAllByStatusIsNotSeen();
    }

    public Feedback createFeedback(Integer userId, String feedbackMessage) {
        User user = userService.getUser(userId);
        Feedback feedback = new Feedback(user, feedbackMessage);
        return feedbackRepository.save(feedback);
    }

    /**
     * Method that sets feedback to seen
     * @param feedbackId    feedback id
     * @return              updated feedback
     */
    @Transactional
    public Feedback setFeedbackSeen(Integer feedbackId) {
        Feedback feedback = getFeedback(feedbackId);
        feedback.setStatus(FeedbackReadStatus.seen);
        return feedback;
    }

    /**
     * Method that deletes feedback from database
     * @param feedbackId    feedback id
     * @return              deleted feedback
     */
    public Feedback deleteFeedback(Integer feedbackId) {
        Feedback feedback = getFeedback(feedbackId);
        feedbackRepository.delete(feedback);
        return feedback;
    }
}
