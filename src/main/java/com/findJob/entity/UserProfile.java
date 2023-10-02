package com.findJob.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String phoneNo;
    private LocalDate dateOfBirth;
    private String city;
    private String description;

    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<String> domains;

    @OneToMany
    private List<Experience> experiences;

    @OneToMany
    private List<Education> education;

    @OneToMany
    private List<Certification> certifications;

    @OneToMany
    private List<Language> languages;

    @OneToMany(mappedBy = "userProfile")
    private List<UserProfileJob> jobs;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;


}
