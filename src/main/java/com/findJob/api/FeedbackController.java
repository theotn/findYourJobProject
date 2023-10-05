package com.findJob.api;

import com.findJob.dto.FeedbackDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestParam("employerProfile") Integer employerProfileId, @RequestParam("user") Integer userId, @RequestBody FeedbackDTO feedbackDTO) throws NotFoundException {

        FeedbackDTO feedback = feedbackService.createFeedback(employerProfileId, userId, feedbackDTO);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<FeedbackDTO> getFeedback(@RequestParam("feedback") Integer feedbackId) throws NotFoundException {

        FeedbackDTO feedback = feedbackService.getFeedback(feedbackId);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackReported() throws NotFoundException {

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbackReported();
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<FeedbackDTO> reportFeedback(@RequestParam("feedback") Integer feedbackId, @RequestParam("user") Integer userId) throws NotFoundException {

        FeedbackDTO feedback = feedbackService.reportFeedback(feedbackId, userId);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<FeedbackDTO> updateFeedback(@RequestParam("feedback") Integer feedbackId, @RequestBody FeedbackDTO feedbackDTO) throws NotFoundException {

        FeedbackDTO feedback = feedbackService.updateFeedback(feedbackId, feedbackDTO);
        return new ResponseEntity<>(feedback, HttpStatus.OK);

    }
    @DeleteMapping
    public ResponseEntity<FeedbackDTO> deleteFeedback(@RequestParam("feedback") Integer feedbackId, @RequestParam("user") Integer userId, @RequestParam("employerProfile") Integer employerProfileId) throws NotFoundException, BadRequestException {

        FeedbackDTO feedback = feedbackService.deleteFeedback(feedbackId, userId, employerProfileId);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

}
