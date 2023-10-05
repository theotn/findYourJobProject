package com.findJob.repository;

import com.findJob.entity.EmployerProfile;
import com.findJob.entity.Feedback;
import com.findJob.entity.Job;
import com.findJob.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("SELECT j FROM Job j WHERE j.reports > 0 ORDER BY j.reports DESC")
    List<Job> getAllJobReported();
    List<Job> findByEmployerProfile(EmployerProfile employerProfile);

}
