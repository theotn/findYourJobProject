package com.findJob.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer stars;
    private Integer reports;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ElementCollection
    private List<Integer> userReportList;

}
