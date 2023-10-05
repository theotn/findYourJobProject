package com.findJob.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private String text;
    private Integer reports;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ElementCollection
    private List<Integer> userReportList;
}
