package com.findJob.api;

import com.findJob.dto.*;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userProfile")
@Validated
public class UserProfileController {

    UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }


    @GetMapping
    public ResponseEntity<UserProfileDTO> getUserProfile(@RequestParam("user") Integer userId) throws NotFoundException, BadRequestException {

        UserProfileDTO userProfileDTO = userProfileService.getUserProfile(userId);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserProfileDTO> updateUserProfile(@RequestParam("profile") Integer profileId, @RequestBody UserProfileDTO userProfile) throws NotFoundException {

        UserProfileDTO userProfileDTO = userProfileService.updateUserProfile(profileId, userProfile);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }


//    @PostMapping("/education")
//    public ResponseEntity<EducationDTO> addEducationToProfile(@RequestParam("profile") Integer profileId, @RequestBody EducationDTO educationDTO) throws NotFoundException {
//
//        EducationDTO education = userProfileService.addEducationToProfile(profileId, educationDTO);
//        return new ResponseEntity<>(education, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/certification")
//    public ResponseEntity<CertificationDTO> addCertificationToProfile(@RequestParam("profile") Integer profileId, @RequestBody CertificationDTO certificationDTO) throws NotFoundException {
//
//        CertificationDTO certification = userProfileService.addCertificationToProfile(profileId, certificationDTO);
//        return new ResponseEntity<>(certification, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/experience")
//    public ResponseEntity<ExperienceDTO> addExperienceToProfile(@RequestParam("profile") Integer profileId, @RequestBody ExperienceDTO experienceDTO) throws NotFoundException {
//
//        ExperienceDTO experience = userProfileService.addExperienceToProfile(profileId, experienceDTO);
//        return new ResponseEntity<>(experience, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/language")
//    public ResponseEntity<LanguageDTO> addLanguageToProfile(@RequestParam("profile") Integer profileId, @RequestBody LanguageDTO languageDTO) throws NotFoundException {
//
//        LanguageDTO language = userProfileService.addLanguageToProfile(profileId, languageDTO);
//        return new ResponseEntity<>(language, HttpStatus.CREATED);
//    }
//
//
//    @DeleteMapping("/education")
//    public ResponseEntity<EducationDTO> deleteEducationFromProfile(@RequestParam("profile") Integer profileId, @RequestParam("education")  Integer educationId) throws NotFoundException {
//
//        EducationDTO education = userProfileService.deleteEducationFromProfile(profileId, educationId);
//        return new ResponseEntity<>(education, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/certification")
//    public ResponseEntity<CertificationDTO> deleteCertificationFromProfile(@RequestParam("profile") Integer profileId, @RequestParam("certification")  Integer certificationId) throws NotFoundException {
//
//        CertificationDTO certification = userProfileService.deleteCertificationFromProfile(profileId, certificationId);
//        return new ResponseEntity<>(certification, HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/experience")
//    public ResponseEntity<ExperienceDTO> deleteExperienceFromProfile(@RequestParam("profile") Integer profileId, @RequestParam("experience")  Integer experienceId) throws NotFoundException {
//
//        ExperienceDTO experience = userProfileService.deleteExperienceFromProfile(profileId, experienceId);
//        return new ResponseEntity<>(experience, HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/language")
//    public ResponseEntity<LanguageDTO> deleteLanguageFromProfile(@RequestParam("profile") Integer profileId, @RequestParam("language")  Integer languageId) throws NotFoundException {
//
//        LanguageDTO language = userProfileService.deleteLanguageFromProfile(profileId, languageId);
//        return new ResponseEntity<>(language, HttpStatus.OK);
//    }


}
