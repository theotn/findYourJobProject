package com.findJob.service.impl;

import com.findJob.dto.*;
import com.findJob.entity.*;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.UserProfileRepository;
import com.findJob.repository.UserRepository;
import com.findJob.service.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private EducationService educationService;
//
//    @Autowired
//    private CertificationService certificationService;
//
//    @Autowired
//    private ExperienceService experienceService;
//
//    @Autowired
//    private LanguageService languageService;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, ModelMapper modelMapper) {

        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileDTO createUserProfile(UserDTO userDTO) {

        User user = modelMapper.map(userDTO, User.class);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        userProfileRepository.save(userProfile);

        return modelMapper.map(userProfile, UserProfileDTO.class);
    }

    @Override
    public UserProfileDTO getUserProfile(Integer userId) throws NotFoundException, BadRequestException {

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found!"));

        if (!user.getIsActive()) throw new BadRequestException("This account is disabled!");

        UserProfile userProfile = userProfileRepository.findByUser(user);

        if (userProfile == null) throw new NotFoundException("User not found!");

        List<Experience> experienceList = userProfile.getExperiences();
        List<ExperienceDTO> experienceDTOList = new ArrayList<>();
        for (Experience e : experienceList) {
            ExperienceDTO experienceDTO = modelMapper.map(e, ExperienceDTO.class);
            experienceDTOList.add(experienceDTO);
        }

        List<Education> educationList = userProfile.getEducation();
        List<EducationDTO> educationDTOList = new ArrayList<>();
        for (Education e : educationList) {
            EducationDTO educationDTO = modelMapper.map(e, EducationDTO.class);
            educationDTOList.add(educationDTO);
        }

        List<Certification> certificationList = userProfile.getCertifications();
        List<CertificationDTO> certificationDTOList = new ArrayList<>();
        for (Certification c : certificationList) {
            CertificationDTO certificationDTO = modelMapper.map(c, CertificationDTO.class);
            certificationDTOList.add(certificationDTO);
        }

        List<Language> languageList = userProfile.getLanguages();
        List<LanguageDTO> languageDTOList = new ArrayList<>();
        for (Language l : languageList) {
            LanguageDTO languageDTO = modelMapper.map(l, LanguageDTO.class);
            languageDTOList.add(languageDTO);
        }

        UserProfileDTO userProfileDTO = modelMapper.map(userProfile, UserProfileDTO.class);
        userProfileDTO.setExperienceDTO(experienceDTOList);
        userProfileDTO.setEducationDTO(educationDTOList);
        userProfileDTO.setCertificationDTO(certificationDTOList);
        userProfileDTO.setLanguageDTO(languageDTOList);

        return userProfileDTO;
    }

    @Override
    public UserProfileDTO updateUserProfile(Integer userProfileId, UserProfileDTO userProfileDTO) throws NotFoundException {

        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));

        if (userProfileDTO.getName() != null) userProfile.setName(userProfileDTO.getName());
        if (userProfileDTO.getPhoneNo() != null) userProfile.setPhoneNo(userProfileDTO.getPhoneNo());
        if (userProfileDTO.getDateOfBirth() != null) userProfile.setDateOfBirth(userProfileDTO.getDateOfBirth());
        if (userProfileDTO.getCity() != null) userProfile.setCity(userProfileDTO.getCity());
        if (userProfileDTO.getDescription() != null) userProfile.setDescription(userProfileDTO.getDescription());

        if (userProfileDTO.getSkills() != null) {

            userProfile.setSkills(userProfileDTO.getSkills());
        }
        if (userProfileDTO.getDomains() != null) {

            userProfile.setDomains(userProfileDTO.getDomains());
        }

        return modelMapper.map(userProfile, UserProfileDTO.class);
    }

//    @Override
//    public EducationDTO addEducationToProfile(Integer userProfileId, EducationDTO educationDTO) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        EducationDTO educationResponse =educationService.createEducation(educationDTO);
//        userProfile.getEducation().add(modelMapper.map(educationResponse, Education.class));
//
//        return educationResponse;
//    }
//
//    @Override
//    public CertificationDTO addCertificationToProfile(Integer userProfileId, CertificationDTO certificationDTO) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        CertificationDTO certificationResponse =  certificationService.createCertification(certificationDTO);
//        userProfile.getCertifications().add(modelMapper.map(certificationResponse, Certification.class));
//
//        return certificationResponse;
//    }
//
//    @Override
//    public ExperienceDTO addExperienceToProfile(Integer userProfileId, ExperienceDTO experienceDTO) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        ExperienceDTO experienceResponse = experienceService.createExperience(experienceDTO);
//        userProfile.getExperiences().add(modelMapper.map(experienceResponse, Experience.class));
//
//        return experienceResponse;
//    }
//
//    @Override
//    public LanguageDTO addLanguageToProfile(Integer userProfileId, LanguageDTO languageDTO) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        LanguageDTO languageResponse = languageService.createLanguage(languageDTO);
//        userProfile.getLanguages().add(modelMapper.map(languageResponse, Language.class));
//
//        return languageResponse;
//    }
//
//    @Override
//    public EducationDTO deleteEducationFromProfile(Integer userProfileId, Integer educationId) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        EducationDTO educationDTO = educationService.deleteEducation(educationId);
//        Education educationToRemove = modelMapper.map(educationDTO, Education.class);
//
//        userProfile.getEducation().remove(educationToRemove);
//
//        return educationDTO;
//    }
//
//    @Override
//    public CertificationDTO deleteCertificationFromProfile(Integer userProfileId, Integer certificationId) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        CertificationDTO certificationDTO = certificationService.deleteCertification(certificationId);
//        Certification certificationToRemove = modelMapper.map(certificationDTO, Certification.class);
//
//        userProfile.getCertifications().remove(certificationToRemove);
//
//        return certificationDTO;
//
//    }
//
//    @Override
//    public ExperienceDTO deleteExperienceFromProfile(Integer userProfileId, Integer experienceId) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        ExperienceDTO experienceDTO = experienceService.deleteExperience(experienceId);
//        Experience experienceToRemove = modelMapper.map(experienceDTO, Experience.class);
//
//        userProfile.getExperiences().remove(experienceToRemove);
//
//        return experienceDTO;
//    }
//
//    @Override
//    public LanguageDTO deleteLanguageFromProfile(Integer userProfileId, Integer languageId) throws NotFoundException {
//
//        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfileId);
//        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new NotFoundException("Profile not found!"));
//
//        LanguageDTO languageDTO = languageService.deleteLanguage(languageId);
//        Language languageToRemove = modelMapper.map(languageDTO, Language.class);
//
//        userProfile.getLanguages().remove(languageToRemove);
//
//        return languageDTO;
//    }


}
