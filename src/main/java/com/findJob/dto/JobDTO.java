package com.findJob.dto;

import com.findJob.entity.Comment;
import com.findJob.entity.UserProfileJob;
import com.findJob.enums.LocationType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JobDTO {

    private Integer id;

    private String title;

    private LocalDate date;

    private String experienceLevel;

    @Enumerated(value = EnumType.STRING)
    private LocationType locationType;

    private String address;

    private String employmentType;

    private List<String> skills;

    private String description;

    private List<Comment> comments;

    private Integer noOfCandidates;

    private Boolean isActive;

    private List<UserDTO> userReportList;

}
