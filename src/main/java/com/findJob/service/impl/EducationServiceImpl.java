package com.findJob.service.impl;

import com.findJob.dto.EducationDTO;
import com.findJob.entity.Education;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.EducationRepository;
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

    public EducationServiceImpl(EducationRepository educationRepository, ModelMapper modelMapper) {

        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EducationDTO createEducation(EducationDTO educationDTO) {

        Education education = modelMapper.map(educationDTO, Education.class);
        educationRepository.save(education);

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
    public EducationDTO deleteEducation(Integer educationId) throws NotFoundException {

        Optional<Education> educationOptional = educationRepository.findById(educationId);
        Education education = educationOptional.orElseThrow(() -> new NotFoundException("Not Found!"));

        educationRepository.delete(education);

        return modelMapper.map(education, EducationDTO.class);
    }

}
