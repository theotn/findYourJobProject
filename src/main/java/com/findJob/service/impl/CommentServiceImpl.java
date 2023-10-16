package com.findJob.service.impl;

import com.findJob.dto.CommentDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.entity.Comment;
import com.findJob.entity.Feedback;
import com.findJob.entity.Job;
import com.findJob.entity.User;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.CommentRepository;
import com.findJob.repository.JobRepository;
import com.findJob.repository.UserRepository;
import com.findJob.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, JobRepository jobRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO createComment(Integer userId, Integer jobId, CommentDTO commentDTO) throws NotFoundException {

        Comment comment = modelMapper.map(commentDTO, Comment.class);

        Optional<Job> jobOptional = jobRepository.findById(jobId);
        Job job = jobOptional.orElseThrow(() -> new NotFoundException("Job not found!"));

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));

        comment.setDate(LocalDate.now());
        comment.setReports(0);
        comment.setUser(user);

        commentRepository.save(comment);
        job.getComments().add(comment);

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO getComment(Integer commentId) throws NotFoundException {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getAllCommentsReported() throws NotFoundException {
        List<Comment> commentList = commentRepository.getAllCommentReported();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment c : commentList) {

            CommentDTO commentDTO = modelMapper.map(c, CommentDTO.class);

            List<UserDTO> userDTOS = new ArrayList<>();

            for (Integer i : c.getUserReportList()) {

                Optional<User> userOptional = userRepository.findById(i);
                User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));
                userDTOS.add(modelMapper.map(user, UserDTO.class));
            }
            commentDTO.setUserReportList(userDTOS);

            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    @Override
    public CommentDTO reportComment(Integer commentId, Integer userId) throws NotFoundException {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        comment.setReports(comment.getReports() + 1);
        if (comment.getUserReportList().contains(commentId))
            throw new NotFoundException("This comment has already been reported!");
        else comment.getUserReportList().add(commentId);

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(Integer commentId, CommentDTO commentDTO) throws NotFoundException {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        if (commentDTO.getText() != null)
            comment.setText(commentDTO.getText());

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO deleteComment(Integer commentId, Integer userId, Integer jobId) throws NotFoundException, BadRequestException {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));

        if (comment.getUser() == user) {
            Optional<Job> jobOptional = jobRepository.findById(jobId);
            Job job = jobOptional.orElseThrow(() -> new NotFoundException("Job not found"));

            job.getComments().remove(comment);
            commentRepository.delete(comment);

            return modelMapper.map(comment, CommentDTO.class);

        }
        throw new BadRequestException("You are not allowed to delete this comment!");
    }
}
