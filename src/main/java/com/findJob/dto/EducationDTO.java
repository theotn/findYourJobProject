package com.findJob.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationDTO {

    private Integer id;

    private String institution;

    private String degree;

    private LocalDate startDate;

    private LocalDate endDate;

    private String city;
}