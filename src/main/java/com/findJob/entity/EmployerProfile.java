package com.findJob.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class EmployerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String headquarter;

    private String domain;

    private String description;

    private Integer noOfEmployees;

    @OneToMany
    private List<Feedback> feedback;

    @OneToOne
    private User user;
}
