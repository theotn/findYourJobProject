package com.findJob.api;

import com.findJob.dto.EmployerProfileDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.EmployerProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employerProfile")
public class EmployerProfileController {

    private EmployerProfileService employerProfileService;

    public EmployerProfileController(EmployerProfileService employerProfileService) {
        this.employerProfileService = employerProfileService;
    }

    @GetMapping
    public ResponseEntity<EmployerProfileDTO> getEmployerProfile(@RequestParam("user") Integer userId) throws NotFoundException, BadRequestException {

        EmployerProfileDTO employerProfileDTO = employerProfileService.getEmployerProfile(userId);
        return new ResponseEntity<>(employerProfileDTO, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<EmployerProfileDTO> updateEmployerProfile(@RequestParam("employerProfile") Integer profileId, @RequestBody EmployerProfileDTO employerProfileDTO) throws NotFoundException {

        EmployerProfileDTO employerProfile = employerProfileService.updateEmployerProfile(profileId, employerProfileDTO);
        return new ResponseEntity<>(employerProfile, HttpStatus.OK);
    }

}
