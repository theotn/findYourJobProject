package com.findJob.api;

import com.findJob.dto.ExperienceDTO;
import com.findJob.exception.NotFoundException;
import com.findJob.service.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    private ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> createExperience(@RequestParam("userProfile") Integer userProfileId, @RequestBody ExperienceDTO experienceDTO) throws NotFoundException {

        ExperienceDTO experience = experienceService.createExperience(userProfileId, experienceDTO);
        return new ResponseEntity<>(experience, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<ExperienceDTO> getExperience(@RequestParam("experience") Integer experienceId) throws NotFoundException {

        ExperienceDTO experience = experienceService.getExperience(experienceId);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ExperienceDTO> updateExperience(@RequestParam("experience") Integer experienceId, @RequestBody ExperienceDTO experienceDTO) throws NotFoundException {

        ExperienceDTO experience = experienceService.updateExperience(experienceId, experienceDTO);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ExperienceDTO> deleteExperience(@RequestParam("userProfile") Integer userProfileId, @RequestParam("experience") Integer experienceId) throws NotFoundException {

        ExperienceDTO experience = experienceService.deleteExperience(userProfileId, experienceId);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }
}

