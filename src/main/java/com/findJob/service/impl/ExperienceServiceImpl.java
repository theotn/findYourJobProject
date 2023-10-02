package com.findJob.service.impl;

import com.findJob.dto.ExperienceDTO;
import com.findJob.entity.Experience;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.ExperienceRepository;
import com.findJob.service.ExperienceService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ExperienceServiceImpl implements ExperienceService {

    private ExperienceRepository experienceRepository;
    private ModelMapper modelMapper;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, ModelMapper modelMapper) {

        this.experienceRepository = experienceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExperienceDTO createExperience(ExperienceDTO experienceDTO) {

        Experience experience = modelMapper.map(experienceDTO, Experience.class);
        experienceRepository.save(experience);

        return modelMapper.map(experience, ExperienceDTO.class);
    }

    @Override
    public ExperienceDTO getExperience(Integer experienceId) throws NotFoundException {

        Optional<Experience> experienceOptional = experienceRepository.findById(experienceId);
        Experience experience = experienceOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        return modelMapper.map(experience, ExperienceDTO.class);
    }

    @Override
    public ExperienceDTO updateExperience(Integer experienceId, ExperienceDTO experienceDTO) throws NotFoundException {

        Optional<Experience> experienceOptional = experienceRepository.findById(experienceId);
        Experience experience = experienceOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        if (experienceDTO.getCompany() != null) experience.setCompany(experienceDTO.getCompany());
        if (experienceDTO.getStartDate() != null) experience.setStartDate(experienceDTO.getStartDate());
        if (experienceDTO.getEndDate() != null) experience.setEndDate(experienceDTO.getEndDate());
        if (experienceDTO.getPosition() != null) experience.setPosition(experienceDTO.getPosition());
        if (experienceDTO.getDescription() != null) experience.setDescription(experienceDTO.getDescription());
        if (experienceDTO.getCity() != null) experience.setCity(experienceDTO.getCity());

        return modelMapper.map(experience, ExperienceDTO.class);
    }

    @Override
    public ExperienceDTO deleteExperience(Integer experienceId) throws NotFoundException {

        Optional<Experience> experienceOptional = experienceRepository.findById(experienceId);
        Experience experience = experienceOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        experienceRepository.delete(experience);

        return modelMapper.map(experience, ExperienceDTO.class);
    }

}
