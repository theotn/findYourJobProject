package com.findJob.repository;

import com.findJob.entity.EmployerProfile;
import com.findJob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerProfileRepository extends JpaRepository<EmployerProfile, Integer> {
    EmployerProfile findByUser(User user);
}
