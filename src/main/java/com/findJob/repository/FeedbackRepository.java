package com.findJob.repository;

import com.findJob.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("SELECT f FROM Feedback f WHERE f.reports > 0 ORDER BY f.reports DESC")
    List<Feedback> getAllFeedbackReported();
}
