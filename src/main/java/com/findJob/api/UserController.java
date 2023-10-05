package com.findJob.api;

import com.findJob.dto.UserDTO;
import com.findJob.entity.User;
import com.findJob.enums.Role;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.EmployerProfileService;
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

    private UserProfileService userProfileService;

    private EmployerProfileService employerProfileService;

    private RestTemplate restTemplate;

    public UserController(UserService userService, UserProfileService userProfileService, EmployerProfileService employerProfileService, RestTemplate restTemplate) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.employerProfileService = employerProfileService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws BadRequestException {

        UserDTO user = userService.createUser(userDTO);

        if(user.getRole() == Role.USER) {

            userProfileService.createUserProfile(user);

        } else if(user.getRole() == Role.COMPANY) {

            employerProfileService.createEmployerProfile(user);
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

    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestParam("user") Integer userId, @Valid @RequestBody UserDTO userDTO) throws BadRequestException, NotFoundException {

        UserDTO user = userService.updateUser(userId,userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



}
