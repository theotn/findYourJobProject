package com.findJob.service.impl;

import com.findJob.dto.EmployerProfileDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.entity.EmployerProfile;
import com.findJob.entity.Feedback;
import com.findJob.entity.User;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.EmployerProfileRepository;
import com.findJob.repository.UserRepository;
import com.findJob.service.EmployerProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
public class EmployerProfileServiceImpl implements EmployerProfileService {

    private EmployerProfileRepository employerProfileRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public EmployerProfileServiceImpl(EmployerProfileRepository employerProfileRepository, ModelMapper modelMapper, UserRepository userRepository) {

        this.employerProfileRepository = employerProfileRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public EmployerProfileDTO createEmployerProfile(UserDTO userDTO) {

        User user = modelMapper.map(userDTO, User.class);

        EmployerProfile employerProfile = new EmployerProfile();
        employerProfile.setUser(user);

        employerProfileRepository.save(employerProfile);

        return modelMapper.map(employerProfile, EmployerProfileDTO.class);
    }

    @Override
    public EmployerProfileDTO getEmployerProfile(Integer userId) throws NotFoundException, BadRequestException {

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found!"));

        if (!user.getIsActive()) throw new BadRequestException("This account is disabled!");

        EmployerProfile employerProfile = employerProfileRepository.findByUser(user);

        if (employerProfile == null) throw new NotFoundException("User not found!");

        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();

        for (Feedback f : employerProfile.getFeedback()) {
            FeedbackDTO feedbackDTO = modelMapper.map(f, FeedbackDTO.class);
            feedbackDTOList.add(feedbackDTO);
        }

        EmployerProfileDTO employerProfileDTO = modelMapper.map(employerProfile, EmployerProfileDTO.class);
        employerProfileDTO.setFeedbackDTOList(feedbackDTOList);

        return employerProfileDTO;
    }

    @Override
    public EmployerProfileDTO updateEmployerProfile(Integer employerProfileId, EmployerProfileDTO employerProfileDTO) throws NotFoundException {

        Optional<EmployerProfile> employerProfileOptional = employerProfileRepository.findById(employerProfileId);
        EmployerProfile employerProfile = employerProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));

        if (employerProfileDTO.getName() != null) employerProfile.setName(employerProfileDTO.getName());
        if (employerProfileDTO.getHeadquarter() != null)
            employerProfile.setHeadquarter(employerProfileDTO.getHeadquarter());
        if (employerProfileDTO.getDomain() != null) employerProfile.setDomain(employerProfileDTO.getDomain());
        if (employerProfileDTO.getDescription() != null)
            employerProfile.setDescription(employerProfileDTO.getDescription());
        if (employerProfileDTO.getNoOfEmployees() != null)
            employerProfile.setNoOfEmployees(employerProfileDTO.getNoOfEmployees());

        return modelMapper.map(employerProfile, EmployerProfileDTO.class);
    }

}
