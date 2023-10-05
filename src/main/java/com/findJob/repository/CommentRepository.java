package com.findJob.repository;

import com.findJob.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.reports > 0 ORDER BY c.reports DESC")
    List<Comment> getAllCommentReported();
}
