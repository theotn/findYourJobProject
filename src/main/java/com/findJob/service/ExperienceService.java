package com.findJob.service;

import com.findJob.dto.ExperienceDTO;
import com.findJob.exception.NotFoundException;

public interface ExperienceService {

    ExperienceDTO createExperience(ExperienceDTO experienceDTO);

    ExperienceDTO getExperience(Integer experienceId) throws NotFoundException;

    ExperienceDTO updateExperience(Integer experienceId, ExperienceDTO experienceDTO) throws NotFoundException;

    ExperienceDTO deleteExperience(Integer experienceId) throws NotFoundException;
}
