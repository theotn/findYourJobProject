package com.findJob.dto;

import com.findJob.entity.Job;
import com.findJob.entity.UserProfile;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserProfileJobDTO {

    private Integer id;

    private UserProfile userProfile;

    private Job job;

    private LocalDateTime dateTime;
}
