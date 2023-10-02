package com.findJob.entity;

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

    @OneToMany
    private List<Comment> comments;
//
//    @ManyToMany
//    @JoinTable(name = "user_profile_job",
//            joinColumns = { @JoinColumn(name = "fk_job")},
//            inverseJoinColumns = { @JoinColumn(name = "fk_user_profile") }
//            )
//    private List<UserProfile> candidates;

    @OneToMany(mappedBy = "job")
    private List<UserProfileJob> candidates;

}
