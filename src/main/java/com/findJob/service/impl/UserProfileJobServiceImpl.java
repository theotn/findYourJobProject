package com.findJob.service.impl;

import com.findJob.dto.UserProfileJobDTO;
import com.findJob.entity.Job;
import com.findJob.entity.UserProfile;
import com.findJob.entity.UserProfileJob;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.JobRepository;
import com.findJob.repository.UserProfileJobRepository;
import com.findJob.repository.UserProfileRepository;
import com.findJob.service.UserProfileJobService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserProfileJobServiceImpl implements UserProfileJobService {

    private UserProfileJobRepository userProfileJobRepository;
    private UserProfileRepository userProfileRepository;
    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    public UserProfileJobServiceImpl(UserProfileJobRepository userProfileJobRepository, UserProfileRepository userProfileRepository, JobRepository jobRepository, ModelMapper modelMapper) {

        this.userProfileJobRepository = userProfileJobRepository;
        this.userProfileRepository = userProfileRepository;
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createInterview(Integer userProfileId, Integer jobId, Map<String, LocalDateTime> updates) throws NotFoundException {

        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Optional<Job> jobOptional = jobRepository.findById(jobId);
        Job job = jobOptional.orElseThrow(() -> new NotFoundException("Job not found!"));

        UserProfileJob userProfileJob = userProfileJobRepository.findByUserProfileAndJob(userProfile, job);
        if (!userProfileJob.getIsAccepted()) userProfileJob.setIsAccepted(true);
        userProfileJob.setDateTime(updates.get("date"));
    }

    @Override
    public void acceptInterview(Integer userProfileId, Integer jobId) throws NotFoundException {

        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Optional<Job> jobOptional = jobRepository.findById(jobId);
        Job job = jobOptional.orElseThrow(() -> new NotFoundException("Job not found!"));
        UserProfileJob userProfileJob = userProfileJobRepository.findByUserProfileAndJob(userProfile, job);
        userProfileJob.setUserAccept(true);
    }

    @Override
    public List<UserProfileJobDTO> getInterviewsForEmployer(Integer jobId) throws NotFoundException {

        Optional<Job> jobOptional = jobRepository.findById(jobId);
        Job job = jobOptional.orElseThrow(() -> new NotFoundException("Job not found!"));

        List<UserProfileJob> userProfileJobList = userProfileJobRepository.findInterviewForEmployerByJob(job);
        List<UserProfileJobDTO> userProfileJobDTOList = new ArrayList<>();
        for (UserProfileJob u : userProfileJobList) {
            userProfileJobDTOList.add(this.modelMapper.map(u, UserProfileJobDTO.class));
        }

        return userProfileJobDTOList;
    }

    @Override
    public List<UserProfileJobDTO> getInterviewsForUser(Integer userProfileId) throws NotFoundException {

        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("UserProfile not found!"));

        List<UserProfileJob> userProfileJobList = userProfileJobRepository.findInterviewForUserProfile(userProfile);
        List<UserProfileJobDTO> userProfileJobDTOList = new ArrayList<>();
        for (UserProfileJob u : userProfileJobList) {
            userProfileJobDTOList.add(this.modelMapper.map(u, UserProfileJobDTO.class));
        }

        return userProfileJobDTOList;
    }
}
