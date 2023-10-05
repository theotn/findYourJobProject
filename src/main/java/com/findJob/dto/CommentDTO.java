package com.findJob.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CommentDTO {

    private Integer id;
    private LocalDate date;
    private String text;
    private Integer reports;
    private UserDTO user;
    private List<UserDTO> userReportList;
}
