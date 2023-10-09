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

    @Query("Select j FROM Job j WHERE :skill IN (Select j2.skills from Job j2 where j.id = j2.id)")
    List<Job> findBySkill(String skill);

    @Query("Select j FROM Job j WHERE j.employerProfile.id = (Select e.id from EmployerProfile e where e.domain = :domain AND j.employerProfile.id = e.id)")
    List<Job> findByDomain(String domain);

}
