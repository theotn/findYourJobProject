package com.findJob.api;

import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.JobDTO;
import com.findJob.dto.UserProfileDTO;
import com.findJob.entity.Job;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/job")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestParam("employerProfile") Integer employerProfileId, @RequestBody JobDTO jobDTO) throws NotFoundException {
        JobDTO job = jobService.createJob(employerProfileId, jobDTO);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<JobDTO> getJob(@RequestParam("job") Integer jobId) throws NotFoundException {
        JobDTO job = jobService.getJob(jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/employer")
    public ResponseEntity<List<JobDTO>> getJobs(@RequestParam("employerProfile") Integer employerProfileId) throws NotFoundException {
        List<JobDTO> jobDTOList = jobService.getJobs(employerProfileId);
        return new ResponseEntity<>(jobDTOList, HttpStatus.OK);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<Set<JobDTO>> getRecommendations(@RequestParam("userProfile") Integer userProfileId, @RequestParam("page") Integer page, @RequestParam("number") Integer number) throws NotFoundException {
        Set<JobDTO> jobs = jobService.getRecommendations(userProfileId, number, page);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/user-applied-list")
    public ResponseEntity<List<JobDTO>> getJobsForUser(@RequestParam("userProfile") Integer userProfileId) throws NotFoundException {
        List<JobDTO> jobDTOList = jobService.getJobsForUser(userProfileId);
        return new ResponseEntity<>(jobDTOList, HttpStatus.OK);
    }

    @GetMapping("/candidates-list")
    public ResponseEntity<List<UserProfileDTO>> getUsersForJob(@RequestParam("job") Integer jobId) throws NotFoundException {
        List<UserProfileDTO> userProfileDTOList = jobService.getUsersForJob(jobId);
        return new ResponseEntity<>(userProfileDTOList, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<JobDTO> updateJob(@RequestParam("job") Integer jobId, @RequestBody JobDTO jobDTO) throws NotFoundException {
        JobDTO job = jobService.updateJob(jobId, jobDTO);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("/new-candidate")
    public ResponseEntity<JobDTO> applyToJob(@RequestParam("userProfile") Integer userProfileId, @RequestParam("job") Integer jobId) throws NotFoundException {
        JobDTO job = jobService.applyToJob(userProfileId, jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<JobDTO> reportJob(@RequestParam("job") Integer jobId, @RequestParam("user") Integer userId) throws NotFoundException {

        JobDTO job = jobService.reportJob(jobId, userId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<JobDTO>> getAllJobReported() throws NotFoundException {

        List<JobDTO> jobList = jobService.getAllJobReported();
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }
}
