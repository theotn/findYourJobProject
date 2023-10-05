package com.findJob.service;

import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.JobDTO;
import com.findJob.dto.UserProfileDTO;
import com.findJob.entity.Job;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

import java.util.List;

public interface JobService {

    JobDTO createJob(Integer employerProfileId, JobDTO jobDTO) throws NotFoundException;
    JobDTO getJob(Integer jobId) throws NotFoundException;
    List<JobDTO> getJobs(Integer employerProfileId) throws NotFoundException;
    JobDTO updateJob(Integer jobId, JobDTO jobDTO) throws NotFoundException;
    JobDTO applyToJob(Integer userProfileId, Integer jobId) throws NotFoundException;
    List<JobDTO> getJobsForUser(Integer userProfileId) throws NotFoundException;
    List<UserProfileDTO> getUsersForJob(Integer jobId) throws NotFoundException;
    JobDTO reportJob(Integer jobId, Integer userId) throws NotFoundException;
    List<JobDTO> getAllJobReported() throws NotFoundException;
    //delete?
}
