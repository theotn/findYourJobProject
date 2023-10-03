package com.findJob.service;

import com.findJob.dto.ExperienceDTO;
import com.findJob.exception.NotFoundException;

public interface ExperienceService {

    ExperienceDTO createExperience(Integer userProfileId, ExperienceDTO experienceDTO) throws NotFoundException;

    ExperienceDTO getExperience(Integer experienceId) throws NotFoundException;

    ExperienceDTO updateExperience(Integer experienceId, ExperienceDTO experienceDTO) throws NotFoundException;

    ExperienceDTO deleteExperience(Integer userProfileId, Integer experienceId) throws NotFoundException;
}
