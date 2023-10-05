package com.findJob.service;

import com.findJob.dto.FeedbackDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

import java.util.List;

public interface FeedbackService {

    FeedbackDTO createFeedback(Integer employerProfileId, Integer userId, FeedbackDTO feedbackDTO) throws NotFoundException;

    FeedbackDTO getFeedback(Integer feedbackId) throws NotFoundException;

    List<FeedbackDTO> getAllFeedbackReported() throws NotFoundException;

    FeedbackDTO reportFeedback(Integer feedbackId, Integer userId) throws NotFoundException;

    //updateFeedback
    FeedbackDTO updateFeedback(Integer feedbackId, FeedbackDTO feedbackDTO) throws NotFoundException;
    FeedbackDTO deleteFeedback(Integer feedbackId, Integer userId, Integer employerProfileId) throws NotFoundException, BadRequestException;
}
