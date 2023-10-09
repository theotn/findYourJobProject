package com.findJob.service.impl;

import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.entity.EmployerProfile;
import com.findJob.entity.Feedback;
import com.findJob.entity.User;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.EmployerProfileRepository;
import com.findJob.repository.FeedbackRepository;
import com.findJob.repository.UserRepository;
import com.findJob.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private EmployerProfileRepository employerProfileRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ModelMapper modelMapper, UserRepository userRepository, EmployerProfileRepository employerProfileRepository) {

        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.employerProfileRepository = employerProfileRepository;
    }

    @Override
    public FeedbackDTO createFeedback(Integer employerProfileId, Integer userId, FeedbackDTO feedbackDTO) throws NotFoundException {

        Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(()-> new NotFoundException("User not found!"));

        Optional<EmployerProfile> employerProfileOptional = employerProfileRepository.findById(employerProfileId);
        EmployerProfile employerProfile = employerProfileOptional.orElseThrow(()-> new NotFoundException("Profile not found!"));

        feedback.setDate(LocalDate.now());
        feedback.setReports(0);
        feedback.setUser(user);

        feedbackRepository.save(feedback);

        employerProfile.getFeedback().add(feedback);

        return modelMapper.map(feedback, FeedbackDTO.class);
    }

    @Override
    public FeedbackDTO getFeedback(Integer feedbackId) throws NotFoundException {

        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        Feedback feedback = feedbackOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        return modelMapper.map(feedback, FeedbackDTO.class);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackReported() throws NotFoundException {

        List<Feedback> feedbackList = feedbackRepository.getAllFeedbackReported();

        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();

        for (Feedback f : feedbackList) {

            FeedbackDTO feedbackDTO = modelMapper.map(f, FeedbackDTO.class);

            List<UserDTO> userDTOS = new ArrayList<>();

            for (Integer i : f.getUserReportList()) {

                Optional<User> userOptional = userRepository.findById(i);
                User user = userOptional.orElseThrow(()->new NotFoundException("User not found!"));
                userDTOS.add(modelMapper.map(user,UserDTO.class));
            }
            feedbackDTO.setUserReportList(userDTOS);

            feedbackDTOList.add(feedbackDTO);
        }

        return feedbackDTOList;
    }

    @Override
    public FeedbackDTO reportFeedback(Integer feedbackId, Integer userId) throws NotFoundException {

        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        Feedback feedback = feedbackOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        feedback.setReports(feedback.getReports() + 1);

        if (feedback.getUserReportList().contains(userId))
            throw new NotFoundException("This feedback has already been reported!");
        else feedback.getUserReportList().add(userId);

        return modelMapper.map(feedback, FeedbackDTO.class);
    }

    @Override
    public FeedbackDTO updateFeedback(Integer feedbackId, FeedbackDTO feedbackDTO) throws NotFoundException {

        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        Feedback feedback = feedbackOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        if(feedbackDTO.getDescription() != null) feedback.setDescription(feedbackDTO.getDescription());
        if(feedbackDTO.getStars() != null) feedback.setStars(feedbackDTO.getStars());

        return modelMapper.map(feedback, FeedbackDTO.class);
    }

    @Override
    public FeedbackDTO deleteFeedback(Integer feedbackId, Integer userId, Integer employerProfileId) throws NotFoundException, BadRequestException {

        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        Feedback feedback = feedbackOptional.orElseThrow(() -> new NotFoundException("Feedback Not found!"));

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User Not found!"));

        if(feedback.getUser()==user) {

            Optional<EmployerProfile> employerProfileOptional = employerProfileRepository.findById(employerProfileId);
            EmployerProfile employerProfile = employerProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));

            employerProfile.getFeedback().remove(feedback);

            feedbackRepository.delete(feedback);

            return modelMapper.map(feedback, FeedbackDTO.class);
        }

        throw new BadRequestException("You are not allowed to delete this feedback!");
    }
}
