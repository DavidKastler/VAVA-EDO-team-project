package vava.edo.service;


import org.springframework.stereotype.Service;
import vava.edo.model.Feedback;
import vava.edo.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }



    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }



    public Optional<Feedback> getFeedbackById(int assId) {
        return feedbackRepository.findById(assId);
    }
}
