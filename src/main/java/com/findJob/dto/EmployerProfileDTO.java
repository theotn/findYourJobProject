package com.findJob.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployerProfileDTO {
    private Integer id;
    private String name;
    private String headquarter;
    private String domain;
    private String description;
    private Integer noOfEmployees;
    private List<FeedbackDTO> feedbackDTOList;
}
