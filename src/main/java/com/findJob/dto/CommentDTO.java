package com.findJob.dto;

import com.findJob.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {

    private Integer id;
    private LocalDate date;
    private String text;
    private Integer reports;
    private User user;
}
