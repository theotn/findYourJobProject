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

    @Column(columnDefinition = "boolean DEFAULT FALSE")
    private Boolean isAccepted;

    private LocalDateTime dateTime;

    @Column(columnDefinition = "boolean DEFAULT FALSE")
    private Boolean userAccept;

}
