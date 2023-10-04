package com.findJob.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FeedbackDTO {

    private Integer id;
    private String description;
    private Integer stars;
    private Integer reports;
    private LocalDate date;
    private UserDTO user;
    private List<UserDTO> userReportList;
}
