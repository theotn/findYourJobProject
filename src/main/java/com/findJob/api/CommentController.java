package com.findJob.api;

import com.findJob.dto.CommentDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.entity.Comment;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestParam("user") Integer userId, @RequestParam("job") Integer jobId, @RequestBody CommentDTO commentDTO) throws NotFoundException {

        CommentDTO comment = commentService.createComment(userId, jobId, commentDTO);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommentDTO> getComment(@RequestParam("comment") Integer commentId) throws NotFoundException {

        CommentDTO commentDTO = commentService.getComment(commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<CommentDTO>> getAllCommentsReported() throws NotFoundException {

        List<CommentDTO> feedbackList = commentService.getAllCommentsReported();
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<CommentDTO> reportComment(@RequestParam("comment") Integer commentId, @RequestParam("user") Integer userId) throws NotFoundException {

        CommentDTO comment = commentService.reportComment(commentId, userId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestParam("comment") Integer commentId, @RequestBody CommentDTO commentDTO) throws NotFoundException {

        CommentDTO comment = commentService.updateComment(commentId, commentDTO);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<CommentDTO> deleteComment(@RequestParam("comment") Integer commentId, @RequestParam("user") Integer userId, @RequestParam("job") Integer jobId) throws NotFoundException, BadRequestException {

        CommentDTO comment = commentService.deleteComment(commentId, userId, jobId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
