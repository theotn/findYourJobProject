package com.findJob.service;

import com.findJob.dto.*;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

public interface UserProfileService {

    UserProfileDTO createUserProfile(UserDTO userDTO) throws BadRequestException;
    UserProfileDTO getUserProfile(Integer userId) throws NotFoundException;
    UserProfileDTO updateUserProfile(Integer userProfileId, UserProfileDTO userProfileDTO) throws NotFoundException;
    EducationDTO addEducationToProfile(Integer userProfileId, EducationDTO educationDTO) throws NotFoundException;
    CertificationDTO addCertificationToProfile(Integer userProfileId, CertificationDTO certificationDTO) throws NotFoundException;
    ExperienceDTO addExperienceToProfile(Integer userProfileId, ExperienceDTO experienceDTO) throws NotFoundException;
    LanguageDTO addLanguageToProfile(Integer userProfileId, LanguageDTO languageDTO) throws NotFoundException;
    EducationDTO deleteEducationFromProfile(Integer userProfileId, Integer educationId) throws NotFoundException;
    CertificationDTO deleteCertificationFromProfile(Integer userProfileId, Integer certificationId) throws NotFoundException;
    ExperienceDTO deleteExperienceFromProfile(Integer userProfileId, Integer experienceId) throws NotFoundException;
    LanguageDTO deleteLanguageFromProfile(Integer userProfileId, Integer languageId) throws NotFoundException;

//    UserProfileDTO addCertification(Integer profileId, CertificationDTO certificationDTO) throws NotFoundException;



}
