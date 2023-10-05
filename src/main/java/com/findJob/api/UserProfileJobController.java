package com.findJob.api;

import com.findJob.dto.UserProfileJobDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.UserProfileJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userProfileJob")
@Validated
public class UserProfileJobController {

    UserProfileJobService userProfileJobService;

    public UserProfileJobController(UserProfileJobService userProfileJobService) {
        this.userProfileJobService = userProfileJobService;
    }

    @PatchMapping
    public ResponseEntity<String> createInterview(@RequestParam("userProfile") Integer userProfileId, @RequestParam("job") Integer jobId, @RequestBody Map<String, LocalDateTime> updates) throws BadRequestException, NotFoundException {

        userProfileJobService.createInterview(userProfileId, jobId, updates);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PatchMapping("/interview")
    public ResponseEntity<String> acceptInterview(@RequestParam("userProfile") Integer userProfileId, @RequestParam("job") Integer jobId) throws BadRequestException, NotFoundException {

        userProfileJobService.acceptInterview(userProfileId, jobId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/employer-interviews")
    public ResponseEntity<List<UserProfileJobDTO>> getInterviewsForEmployer(@RequestParam("job") Integer jobId) throws NotFoundException {

        List<UserProfileJobDTO> userProfileJobDTOList = userProfileJobService.getInterviewsForEmployer(jobId);
        return new ResponseEntity<>(userProfileJobDTOList, HttpStatus.OK);
    }

    @GetMapping("/user-interviews")
    public ResponseEntity<List<UserProfileJobDTO>> getInterviewsForUser(@RequestParam("userProfile") Integer userProfileId) throws NotFoundException {

        List<UserProfileJobDTO> userProfileJobDTOList = userProfileJobService.getInterviewsForUser(userProfileId);
        return new ResponseEntity<>(userProfileJobDTOList, HttpStatus.OK);
    }
}
