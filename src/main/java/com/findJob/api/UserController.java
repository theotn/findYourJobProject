package com.findJob.api;

import com.findJob.dto.UserDTO;
import com.findJob.entity.User;
import com.findJob.enums.Role;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.UserProfileService;
import com.findJob.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    private RestTemplate restTemplate;

    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws BadRequestException {

        UserDTO user = userService.createUser(userDTO);

        if(user.getRole() == Role.USER) {

            userProfileService.createUserProfile(user);

        } else if(user.getRole() == Role.COMPANY) {

        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody UserDTO userDTO) throws BadRequestException, NotFoundException {

        UserDTO user = userService.loginUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam("user") Integer userId) throws BadRequestException, NotFoundException {

        UserDTO user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestParam("user") Integer userId,@RequestBody UserDTO userDTO) throws BadRequestException, NotFoundException {

        UserDTO user = userService.updateUser(userId,userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PatchMapping("/")
//    public ResponseEntity<UserDTO> disableUser(@RequestParam("user") Integer userId) throws NotFoundException {
//
//        UserDTO user = userService.disableUser(userId);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

//    @PostMapping("/feedback")
//    public ResponseEntity<String> reportFeedback(@RequestParam("user") Integer userId, @RequestParam("feedback") Integer feedbackId) throws NotFoundException {
//
//        userService.reportFeedback(userId, feedbackId);
//        return new ResponseEntity<>("succes", HttpStatus.OK);
//    }

}
