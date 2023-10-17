package com.findJob.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.findJob.enums.LocationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private LocalDate date;

    private String experienceLevel;

    @Enumerated(value = EnumType.STRING)
    private LocationType locationType;

    private String address;

    private String employmentType;

    @ElementCollection
    private List<String> skills;

    private String description;

    private Boolean isActive;

    private Integer reports;

    @ManyToOne
    private EmployerProfile employerProfile;

    @OneToMany
    private List<Comment> comments;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<UserProfileJob> candidates;

    @ElementCollection
    private List<Integer> userReportList;

}
