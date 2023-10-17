package com.findJob.api;

import com.findJob.dto.*;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userProfile")
@Validated
public class UserProfileController {

    UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDTO> getUserProfile(@RequestParam("user") Integer userId) throws NotFoundException, BadRequestException {

        UserProfileDTO userProfileDTO = userProfileService.getUserProfile(userId);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserProfileDTO> updateUserProfile(@RequestParam("profile") Integer profileId, @RequestBody UserProfileDTO userProfile) throws NotFoundException {

        UserProfileDTO userProfileDTO = userProfileService.updateUserProfile(profileId, userProfile);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }

}
