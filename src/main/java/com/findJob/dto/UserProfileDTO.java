package com.findJob.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserProfileDTO {

    private Integer id;
    private String name;
    private String phoneNo;
    private LocalDate dateOfBirth;
    private String city;
    private String description;
    private List<String> skills;
    private List<String> domains;

    private List<ExperienceDTO> experienceDTO;

    private List<EducationDTO> educationDTO;

    private List<CertificationDTO> certificationDTO;

    private List<LanguageDTO> languageDTO;

//    private List<UserProfileJob> jobs;
//
//    private User user;
}
