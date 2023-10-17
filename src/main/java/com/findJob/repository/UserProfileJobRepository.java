package com.findJob.repository;

import com.findJob.entity.Job;
import com.findJob.entity.UserProfile;
import com.findJob.entity.UserProfileJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProfileJobRepository extends JpaRepository<UserProfileJob, Integer> {

    List<UserProfileJob> findByUserProfile(UserProfile userProfile);
    List<UserProfileJob> findByJob(Job job);
    UserProfileJob findByUserProfileAndJob(UserProfile userProfile, Job job);
    @Query("SELECT u FROM UserProfileJob u WHERE u.job= :job AND u.userAccept = true")
    List<UserProfileJob> findInterviewForEmployerByJob(Job job);

    @Query("SELECT u FROM UserProfileJob u WHERE u.userProfile= :userProfile AND u.isAccepted = true")
    List<UserProfileJob> findInterviewForUserProfile(UserProfile userProfile);
}
