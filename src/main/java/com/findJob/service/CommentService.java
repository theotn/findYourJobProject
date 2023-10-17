package com.findJob.service;

import com.findJob.dto.CommentDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Integer userId, Integer jobId, CommentDTO commentDTO) throws NotFoundException;

    CommentDTO getComment(Integer commentId) throws NotFoundException;

    List<CommentDTO> getAllCommentsReported() throws NotFoundException;

    CommentDTO reportComment(Integer commentId, Integer userId) throws NotFoundException;

    CommentDTO updateComment(Integer commentId, CommentDTO commentDTO) throws NotFoundException;

    CommentDTO deleteComment(Integer commentId, Integer userId, Integer jobId) throws NotFoundException, BadRequestException;
}
