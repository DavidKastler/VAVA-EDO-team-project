package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Feedback;
import vava.edo.service.FeedbackService;
import vava.edo.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Class that provides endpoints for operations with feedbacks
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserService userService;


    @Autowired
    public FeedbackController(FeedbackService feedbackService, UserService userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestParam(value = "token") int token,
                                                   @RequestBody String feedbackMessage) {
        return null;
    }


    /**
     * Endpoint returning a list of feedbacks
     *
     * @param token user account rights verification
     * @return list of feedbacks
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(@RequestParam(value = "token") int token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.getAllFeedbacks(), HttpStatus.OK);
    }


    /**
     * Endpoint returning a feedback
     *
     * @param token user account rights verification
     * @param fbId  id of wanted feedback
     * @return feedback
     */
    @GetMapping(value = "/get/{fbId}")
    public ResponseEntity<Optional<Feedback>> getFeedbacksById(@RequestParam(value = "token") int token,
                                                               @PathVariable(value = "fbId") int fbId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.getFeedbackById(fbId), HttpStatus.OK);
    }


}

