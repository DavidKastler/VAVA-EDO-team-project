package vava.edo.service;


import org.springframework.stereotype.Service;
import vava.edo.model.Feedback;
import vava.edo.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service that operates over 'feedback' database table
 */
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    /**
     * Method returns all feedbacks in database
     *
     * @return list of all feedbacks
     */
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }


    /**
     * Method returns feedback with given ID
     *
     * @param assId getting feedback ID
     *              * @return    feedback
     */
    public Optional<Feedback> getFeedbackById(int assId) {
        return feedbackRepository.findById(assId);
    }
}
