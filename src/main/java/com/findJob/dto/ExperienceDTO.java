package com.findJob.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExperienceDTO {
    private Integer id;

    private String company;

    private LocalDate startDate;

    private LocalDate endDate;

    private String position;

    private String description;

    private String city;
}
