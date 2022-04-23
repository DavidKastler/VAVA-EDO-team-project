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
 * Class that provides endpoints for operations with feedback
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

    /**
     * Endpoint to create feedback
     * @param token             user id
     * @param feedbackMessage   message with feedback
     * @return                  created feedback
     */
    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestParam(value = "token") Integer token,
                                                   @RequestBody String feedbackMessage) {
        return new ResponseEntity<>(feedbackService.createFeedback(token, feedbackMessage), HttpStatus.CREATED);
    }

    /**
     * Endpoint that sets feedback as seen based on given feedback id
     * @param token user is
     * @param fbId  feedback id
     * @return      updated feedback
     */
    @PutMapping("seen/{fbId}")
    public ResponseEntity<Feedback> setFeedbackSeen(@RequestParam(value = "token") Integer token,
                                                    @PathVariable(value = "fbId") Integer fbId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.setFeedbackSeen(fbId), HttpStatus.OK);
    }

    /**
     * Endpoint that deletes feedback based on given feedback id
     * @param token user is
     * @param fbId  feedback id
     * @return      deleted feedback
     */
    @DeleteMapping("delete/{fbId}")
    public ResponseEntity<Feedback> deleteFeedback(@RequestParam(value = "token") Integer token,
                                                   @PathVariable(value = "fbId") Integer fbId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.deleteFeedback(fbId), HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint returning a list of all unseen feedback
     * @param token user account rights verification
     * @return list of unseen feedback
     */
    @GetMapping(value = "/new")
    public ResponseEntity<List<Feedback>> getAllUnseenFeedback(@RequestParam(value = "token") Integer token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.getAllUnseenFeedback(), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of feedback
     * @param token user account rights verification
     * @return list of feedback
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Feedback>> getAllFeedback(@RequestParam(value = "token") Integer token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.getAllFeedback(), HttpStatus.OK);
    }

    /**
     * Endpoint returning a feedback
     * @param token user account rights verification
     * @param fbId  id of wanted feedback
     * @return feedback
     */
    @GetMapping(value = "/get/{fbId}")
    public ResponseEntity<Feedback> getFeedbacksById(@RequestParam(value = "token") int token,
                                                     @PathVariable(value = "fbId") int fbId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(feedbackService.getFeedback(fbId), HttpStatus.OK);
    }


}

