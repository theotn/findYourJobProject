package com.findJob.repository;

import com.findJob.entity.User;
import com.findJob.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
    UserProfile findByUser(User user);
}
