package com.findJob.api;

import com.findJob.dto.EducationDTO;
import com.findJob.exception.NotFoundException;
import com.findJob.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
public class EducationController {

    private EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    public ResponseEntity<EducationDTO> createEducation(@RequestParam("userProfile") Integer userProfileId, @RequestBody EducationDTO educationDTO) throws NotFoundException {

        EducationDTO education = educationService.createEducation(userProfileId, educationDTO);
        return new ResponseEntity<>(education, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<EducationDTO> getEducation(@RequestParam("education") Integer educationId) throws NotFoundException {

        EducationDTO education = educationService.getEducation(educationId);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<EducationDTO> updateEducation(@RequestParam("education") Integer educationId, @RequestBody EducationDTO educationDTO) throws NotFoundException {

        EducationDTO education = educationService.updateEducation(educationId, educationDTO);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<EducationDTO> deleteEducation(@RequestParam("userProfile") Integer userProfileId, @RequestParam("education") Integer educationId) throws NotFoundException {

        EducationDTO education = educationService.deleteEducation(userProfileId, educationId);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }
}
