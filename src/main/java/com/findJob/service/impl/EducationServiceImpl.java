package com.findJob.service.impl;

import com.findJob.dto.EducationDTO;
import com.findJob.entity.Education;
import com.findJob.entity.UserProfile;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.EducationRepository;
import com.findJob.repository.UserProfileRepository;
import com.findJob.service.EducationService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;

    private ModelMapper modelMapper;

    private UserProfileRepository userProfileRepository;

    public EducationServiceImpl(EducationRepository educationRepository, ModelMapper modelMapper, UserProfileRepository userProfileRepository) {

        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public EducationDTO createEducation(Integer userProfileId, EducationDTO educationDTO) throws NotFoundException {

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = optionalUserProfile.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Education education = modelMapper.map(educationDTO, Education.class);
        educationRepository.save(education);

        userProfile.getEducation().add(education);

        return modelMapper.map(education, EducationDTO.class);
    }

    @Override
    public EducationDTO getEducation(Integer educationId) throws NotFoundException {

        Optional<Education> educationOptional = educationRepository.findById(educationId);
        Education education = educationOptional.orElseThrow(() -> new NotFoundException("Not Found!"));

        return modelMapper.map(education, EducationDTO.class);
    }

    @Override
    public EducationDTO updateEducation(Integer educationId, EducationDTO educationDTO) throws NotFoundException {

        Optional<Education> educationOptional = educationRepository.findById(educationId);
        Education education = educationOptional.orElseThrow(() -> new NotFoundException("Not Found!"));

        if (educationDTO.getInstitution() != null) education.setInstitution(educationDTO.getInstitution());
        if (educationDTO.getDegree() != null) education.setDegree(educationDTO.getDegree());
        if (educationDTO.getCity() != null) education.setCity(educationDTO.getCity());
        if (educationDTO.getStartDate() != null) education.setStartDate(educationDTO.getStartDate());
        if (educationDTO.getEndDate() != null) education.setEndDate(educationDTO.getEndDate());

        return modelMapper.map(education, EducationDTO.class);
    }

    @Override
    public EducationDTO deleteEducation(Integer userProfileId, Integer educationId) throws NotFoundException {

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = optionalUserProfile.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Optional<Education> educationOptional = educationRepository.findById(educationId);
        Education education = educationOptional.orElseThrow(() -> new NotFoundException("Not Found!"));

        userProfile.getEducation().remove(education);
        educationRepository.delete(education);

        return modelMapper.map(education, EducationDTO.class);
    }

}
