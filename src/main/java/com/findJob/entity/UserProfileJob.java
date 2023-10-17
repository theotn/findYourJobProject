package com.findJob.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserProfileJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;

    private Boolean isAccepted = false;

    private LocalDateTime dateTime;

    private Boolean userAccept = false;
}
