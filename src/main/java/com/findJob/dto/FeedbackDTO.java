package com.findJob.dto;

import lombok.Data;

import java.util.List;

@Data
public class FeedbackDTO {

    private Integer id;
    private String description;
    private Integer stars;
    private Integer reports;
    private UserDTO user;
    private List<UserDTO> userReportList;
}
