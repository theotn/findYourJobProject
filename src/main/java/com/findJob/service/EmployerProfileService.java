package com.findJob.service;

import com.findJob.dto.EmployerProfileDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

public interface EmployerProfileService {

    EmployerProfileDTO createEmployerProfile(UserDTO userDTO);
    EmployerProfileDTO getEmployerProfile(Integer userId) throws NotFoundException, BadRequestException;
    EmployerProfileDTO updateEmployerProfile(Integer employerProfileId, EmployerProfileDTO employerProfileDTO) throws NotFoundException;
}
