package com.findJob.service;

import com.findJob.dto.UserProfileJobDTO;
import com.findJob.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserProfileJobService {

    void createInterview(Integer userProfileId, Integer jobId, Map<String, LocalDateTime> updates) throws NotFoundException;

    void acceptInterview(Integer userProfileId, Integer jobId) throws NotFoundException;

    List<UserProfileJobDTO> getInterviewsForEmployer(Integer jobId) throws NotFoundException;

    List<UserProfileJobDTO> getInterviewsForUser(Integer userProfileId) throws NotFoundException;
}
