package com.findJob.service;

import com.findJob.dto.EducationDTO;
import com.findJob.exception.NotFoundException;

public interface EducationService {

    EducationDTO createEducation(Integer userProfileId, EducationDTO educationDTO) throws NotFoundException;

    EducationDTO getEducation(Integer educationId) throws NotFoundException;

    EducationDTO updateEducation(Integer educationId, EducationDTO educationDTO) throws NotFoundException;

    EducationDTO deleteEducation(Integer userProfileId, Integer educationId) throws NotFoundException;
}
